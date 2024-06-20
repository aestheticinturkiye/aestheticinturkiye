import { useState } from "react";
import { BadgeGroup, BadgeMessage } from "@components/Badge";
import { Content } from "@components/Content";
import { MotionBTTContainer } from "@components/Motion";
import { SectionContainer } from "@components/Section";
import { PageTitle } from "@components/Title";
import Image from "next/image";

export const HomeBanner = () => {
    const [email, setEmail] = useState("");
    const [message, setMessage] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMessage("");

        try {
            const res = await fetch("https://esthomy.com/8080/v1/subscribe", {
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
        <SectionContainer className="page-banner--container py-16">
            <SectionContainer className="page-banner--inner-container wrap wrap-px z-10">
                {/* Appear First */}
                <MotionBTTContainer transition={{ delay: 0.2, duration: 0.5 }}>
                    <BadgeGroup alignment="center">
                        <BadgeMessage>Discover Esthomy!</BadgeMessage>
                    </BadgeGroup>
                </MotionBTTContainer>
                {/* Appear Second */}
                <MotionBTTContainer transition={{ delay: 0.4, duration: 0.5 }}>
                    <PageTitle className="text-center mx-auto" type="heavy">
                        Transform Your Look with World-Class Aesthetic
                        Treatments in Turkey
                    </PageTitle>
                </MotionBTTContainer>
                {/* Appear Third */}
                <MotionBTTContainer transition={{ delay: 0.6, duration: 0.5 }}>
                    <Content className="text-center" alignment="center">
                        <p>
                            Connect with Trusted Brokers and Find the Best
                            Clinics for Hair Transplants, Dentistry, and More
                        </p>
                    </Content>
                    <div className="mt-6 mb-16 text-center ">
                        <form
                            onSubmit={handleSubmit}
                            className="email-subscription-form"
                        >
                            <div
                                id="banner-email"
                                className="flex gap-4 justify-center align-center"
                            >
                                <input
                                    type="email"
                                    name="email"
                                    id="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    color="gray"
                                    className="w-96 focus:border-[#F4F4F4] border-solid border border-3 rounded p-2"
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
                </MotionBTTContainer>
                {/* Appear Fourth */}
                <MotionBTTContainer transition={{ delay: 0.8, duration: 0.5 }}>
                    <div className="page-banner--image">
                        <Image
                            src="/Order Placed.png"
                            width={512}
                            height={256}
                            alt="Order Placed"
                            objectFit="cover"
                            className="mx-auto"
                        />
                    </div>
                </MotionBTTContainer>
            </SectionContainer>
        </SectionContainer>
    );
};
