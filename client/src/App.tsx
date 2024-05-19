import { ThemeProvider } from "@/components/theme-provider";
import { Outlet } from "react-router-dom";
import { cn } from "./lib/utils";
import { Toaster } from "@/components/ui/toaster";

function App() {
  return (
    <div className={cn("min-h-screen bg-background font-sans antialiased")}>
      <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
        <Toaster />
        <Outlet />
      </ThemeProvider>
    </div>
  );
}

export default App;
