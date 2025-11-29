import { create } from 'zustand';
import { cartAPI } from '../services/api';
import toast from 'react-hot-toast';

export const useCartStore = create((set, get) => ({
  cart: null,
  isLoading: false,
  error: null,

  fetchCart: async () => {
    set({ isLoading: true });
    try {
      const response = await cartAPI.get();
      set({ cart: response.data.data, isLoading: false });
    } catch (error) {
      set({ error: error.message, isLoading: false });
    }
  },

  addToCart: async (productId, quantity = 1) => {
    set({ isLoading: true });
    try {
      const response = await cartAPI.addItem({ productId, quantity });
      set({ cart: response.data.data, isLoading: false });
      toast.success('Added to cart!');
      return { success: true };
    } catch (error) {
      set({ isLoading: false });
      toast.error(error.response?.data?.message || 'Failed to add to cart');
      return { success: false };
    }
  },

  updateQuantity: async (productId, quantity) => {
    set({ isLoading: true });
    try {
      const response = await cartAPI.updateItem(productId, quantity);
      set({ cart: response.data.data, isLoading: false });
    } catch (error) {
      set({ isLoading: false });
      toast.error(error.response?.data?.message || 'Failed to update cart');
    }
  },

  removeFromCart: async (productId) => {
    set({ isLoading: true });
    try {
      const response = await cartAPI.removeItem(productId);
      set({ cart: response.data.data, isLoading: false });
      toast.success('Item removed from cart');
    } catch (error) {
      set({ isLoading: false });
      toast.error(error.response?.data?.message || 'Failed to remove item');
    }
  },

  clearCart: async () => {
    set({ isLoading: true });
    try {
      await cartAPI.clear();
      set({ cart: null, isLoading: false });
    } catch (error) {
      set({ isLoading: false });
    }
  },

  getItemCount: () => {
    const cart = get().cart;
    return cart?.totalItems || 0;
  },
}));
