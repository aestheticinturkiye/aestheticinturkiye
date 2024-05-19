import { create } from "zustand";

const initialState = {
  accessToken: "",
  user: {},
};
const useAuth = create((set) => ({
  ...initialState,
  setAccessToken: (token) => set((state) => ({ accessToken: token })),
  setUser: (user) => set((state) => ({ user })),
  reset: () => set(initialState),
}));

export default useAuth;
