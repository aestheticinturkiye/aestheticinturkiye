import Link from "next/link";
import Image from "next/image";
import { SectionContainer } from "@components/Section";
import { Nav } from "@components/Nav";
import { ButtonGroup, Button } from "@components/Button";
import { Icon } from "@iconify/react";

export const Header = () => {
    return (
        <header
            id="header"
            className="header fixed left-0 w-full z-30 top-0 bg-white backdrop-filter backdrop-blur-md bg-opacity-50"
        >
            <SectionContainer className="header--container wrap wrap-px flex justify-between items-center">
                <div className="flex items-center">
                    <Link href="/">
                        <Image
                            src="/Slice 3.svg"
                            alt="logo"
                            height={44}
                            width={148.36} // adjust width accordingly
                            priority
                            className="logo-image"
                        />
                    </Link>
                </div>
                <SectionContainer className="flex items-center ml-auto">
                    <Nav />
                    <ButtonGroup className="hidden md:block">
                        <a
                            role="button"
                            href="/#footer-email"
                            className="btn btn--secondary ml-4"
                        >
                            Newsletter
                            <Icon icon="material-symbols:arrow-forward-rounded" />
                        </a>
                    </ButtonGroup>
                </SectionContainer>
            </SectionContainer>
        </header>
    );
};
