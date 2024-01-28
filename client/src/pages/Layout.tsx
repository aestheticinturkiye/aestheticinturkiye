import { Outlet } from "react-router-dom";
import { Navbar } from "../components/navbar";

const Layout = () => {
  return (
    <>
      <div className="fixed top-0 w-full z-10">
        <Navbar />
      </div>

      <main className="max-w-7xl mx-auto mt-20 px-2 sm:px-6 lg:px-8 py-4">
        <Outlet />
      </main>
    </>
  );
};

export default Layout;
