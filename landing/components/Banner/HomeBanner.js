import { BadgeGroup, BadgeIcon, BadgeMessage } from "@components/Badge";
import { Button, ButtonGroup } from "@components/Button";
import { Content } from "@components/Content";
import { MotionBTTContainer, MotionInfiniteImage } from "@components/Motion";
import { SectionContainer } from "@components/Section";
import { PageTitle } from "@components/Title";
import { Icon } from "@iconify/react";
import Image from "next/image";

export const HomeBanner = () => {
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
                    <div className="mt-6 mb-16 text-center">
                        <ButtonGroup alignment="center">
                            <Button href="#features">Features</Button>
                            <a
                                role="button"
                                href=""
                                className="btn btn--secondary"
                            >
                                Get Template
                                <Icon icon="material-symbols:arrow-forward-rounded" />
                            </a>
                        </ButtonGroup>
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
