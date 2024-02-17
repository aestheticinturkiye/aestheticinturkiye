import { Routes, Route } from "react-router-dom";
import Layout from "./pages/Layout";
import { Register } from "./pages/Register";
import { Login } from "./pages/Login";
import Home from "./pages/Home";
import MailPage from "./pages/dashboard/page";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route path="/register" element={<Home />} />
        <Route path="/dashboard" element={<MailPage />} />

        {/* public routes */}
        {/* <Route index element={<Navigate to="/home" replace />} />
        <Route path="register" element={<Register />} />
        <Route path="login" element={<Login />} />
        <Route element={<RequireAuth />}>
          <Route path="home" element={<Home />} />
          <Route path="find-partner" element={<Form />} />
          <Route path="messages" element={null} />
          <Route path="notifications" element={null} />
          <Route path=":id" element={null} />
        </Route> */}

        {/* partner */}
        {/* <Route element={<RequireAuth />}>
          <Route path="partner">
            <Route index element={<Dashboard />} />

            <Route path="" element={<Form />} />
          </Route>
        </Route> */}
        {/* catch all */}
        {/* <Route path="*" element={<NotFound />} /> */}
      </Route>
      <Route path="/register" element={<Register />} />
      <Route path="/login" element={<Login />} />
    </Routes>
  );
}

export default App;
