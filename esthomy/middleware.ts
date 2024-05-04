import { NextResponse, type NextRequest } from "next/server";
import * as data from "./constants/redirect-data.json";
import { updateSession } from "./utils/supabase/middleware";

type RedirectData = {
  [key: string]: {
    destination: string;
    permanent: boolean;
  };
};
export async function middleware(request: NextRequest) {
  const redirectEntry = (data as RedirectData)[request.nextUrl.pathname];
  const url = request.nextUrl.clone();
  url.pathname = redirectEntry?.destination;
  // console.log("redirectEntry", redirectEntry);

  if (redirectEntry) {
    const statusCode = redirectEntry.permanent ? 308 : 307;
    return NextResponse.redirect(url, statusCode);
  }

  // No redirect found, continue without redirecting
  // return NextResponse.next();
  return await updateSession(request);
}

export const config = {
  matcher: [
    /*
     * Match all request paths except for the ones starting with:
     * - _next/static (static files)
     * - _next/image (image optimization files)
     * - favicon.ico (favicon file)
     * Feel free to modify this pattern to include more paths.
     */
    "/((?!_next/static|_next/image|favicon.ico|.*\\.(?:svg|png|jpg|jpeg|gif|webp)$).*)",
  ],
};
