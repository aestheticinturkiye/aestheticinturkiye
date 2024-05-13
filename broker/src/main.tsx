import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import "./index.css";
import {
  createBrowserRouter,
  Navigate,
  RouterProvider,
} from "react-router-dom";
import LoginPage from "./pages/Login.tsx";
import Layout from "./pages/Layout.tsx";
import { Dashboard } from "./pages/Dashboard";
import { Settings } from "./pages/Settings";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "/",
        element: <Layout />,
        children: [
          { path: "", element: <Navigate to="dashboard" /> },
          {
            path: "dashboard",
            element: <Dashboard />,
            children: [],
          },
          {
            path: "settings",
            element: <Settings />,
            children: [],
          },
        ],
      },

      { path: "login", element: <LoginPage /> },
      { path: "*", element: <Navigate to="/dashboard" /> },
      { path: "/", element: <Navigate to="/dashboard" /> },
    ],
  },
]);
ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <RouterProvider router={router} />

    {/* <App /> */}
  </React.StrictMode>
);
