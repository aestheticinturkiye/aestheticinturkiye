import { useState } from "react";
import { SectionContainer } from "@components/Section";
import Link from "next/link";
import Image from "next/image";

const DATA = [
    {
        title: "Template",
        items: [
            {
                label: "Features",
                href: "#features"
            },
            {
                label: "Testimonials",
                href: "#testimonials"
            },
            {
                label: "FAQ",
                href: "#faq"
            }
        ]
    },
    {
        title: "Company",
        items: [
            {
                label: "About",
                href: "",
                target: "_blank"
            },
            {
                label: "Twitter",
                href: "",
                target: "_blank"
            },
            {
                label: "Instagram",
                href: "",
                target: "_blank"
            },
            {
                label: "Facebook",
                href: "",
                target: "_blank"
            }
        ]
    }
];

export const Footer = () => {
    const [email, setEmail] = useState("");
    const [message, setMessage] = useState("");
    const date = new Date();
    const year = date.getFullYear();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMessage("");

        try {
            const res = await fetch("/api/subscribe", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ email })
            });

            const result = await res.json();

            if (res.ok) {
                setMessage("Subscription successful!");
                setEmail("");
            } else {
                setMessage(result.message || "Subscription failed.");
            }
        } catch (error) {
            setMessage("An error occurred. Please try again.");
        }
    };

    return (
        <footer id="footer" className="bg-white">
            <SectionContainer className="footer--container wrap wrap-px relative z-10">
                <div className="footer--content-container py-16 grid grid-cols-1 md:grid-cols-12 gap-8">
                    {/* Logo and Button */}
                    <div className="col-span-12 md:col-span-3">
                        <div className="footer--logo grid gap-8">
                            <Link href="/">
                                <Image
                                    src="/Slice 3.svg"
                                    alt="logo"
                                    className="h-10 w-auto"
                                    height="25"
                                    width="100"
                                    priority
                                />
                            </Link>
                        </div>
                    </div>

                    {/* Footer Links */}
                    <div className="col-span-12 md:col-span-4 grid grid-cols-2 gap-4">
                        {DATA.map((footerLinks) => (
                            <div
                                key={footerLinks.title}
                                className="footer-menu--container"
                            >
                                <h3 className="font-bold text-base mb-2">
                                    {footerLinks.title}
                                </h3>
                                <ul className="footer-menu--list">
                                    {footerLinks.items.map((footerItem) => (
                                        <li
                                            key={footerItem.label}
                                            className="footer-menu--list-item gap-2"
                                        >
                                            <a
                                                className="mb-2 block w-auto font-medium transition-colors duration-300 hover:underline"
                                                href={footerItem.href}
                                                target={footerItem.target}
                                            >
                                                {footerItem.label}
                                            </a>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        ))}
                    </div>

                    {/* Email Subscription Form */}
                    <div
                        id="footer-email"
                        className="col-span-12 md:col-span-5"
                    >
                        <form
                            onSubmit={handleSubmit}
                            className="email-subscription-form"
                        >
                            <h3 className="font-bold text-base mb-2">
                                Subscribe to our newsletter
                            </h3>
                            <div className="flex gap-4">
                                <input
                                    type="email"
                                    name="email"
                                    id="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    color="gray"
                                    className="focus:border-[#F4F4F4] border-solid border-[#F3F5F8] border border-3 rounded p-2"
                                    placeholder="Enter your email"
                                    required
                                />
                                <button
                                    type="submit"
                                    className="btn btn--secondary"
                                >
                                    Subscribe
                                </button>
                            </div>
                            {message && <p>{message}</p>}
                        </form>
                    </div>
                </div>
            </SectionContainer>

            {/* Footer Credits */}
            <SectionContainer className="footer-credits relative z-10">
                <div className="wrap wrap-px py-6">
                    <p className="my-0">
                        © {year} Esthomy. All rights reserved.
                    </p>
                </div>
            </SectionContainer>
            <div className="footer--background"></div>
        </footer>
    );
};
