import React, { createContext, useState, ReactNode } from "react";

interface AuthProviderProps {
  children: ReactNode;
}

const AuthContext = createContext<any>({});

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [auth, setAuth] = useState({});

  return (
    <AuthContext.Provider value={{ auth, setAuth }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthContext;
