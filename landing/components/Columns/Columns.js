import { SectionContainer } from "@components/Section";
import { Icon } from "@iconify/react";
import { v4 as uuid } from "uuid";

const ColumnData = [
    {
        id: uuid(),
        title: "Emily J.",
        icon: "carbon:user-avatar-filled-alt",
        content:
            "I had an amazing experience with my hair transplant in Turkey. The process was smooth, and the results are fantastic!"
    },
    {
        id: uuid(),
        title: "Jason F.",
        icon: "carbon:user-avatar-filled-alt",
        content:
            "Choosing dental implants in Turkey was the best decision. Great service and affordable prices!"
    },
    {
        id: uuid(),
        title: "Miguel J.",
        icon: "carbon:user-avatar-filled-alt",
        content:
            "The brokers helped me find the perfect clinic for my breast aesthetics. I couldn't be happier with the outcome!"
    },
    {
        id: uuid(),
        title: "Sarah M.",
        icon: "carbon:user-avatar-filled-alt",
        content:
            "The brokers helped me find the perfect clinic for my breast aesthetics. I couldn't be happier with the outcome!"
    }
];

export const Columns = () => {
    return (
        <SectionContainer className="benefits-lists grid gap-x-8 gap-y-16 grid-cols-1 md:grid-cols-2 lg:grid-cols-4 mt-16">
            {ColumnData.map((item) => (
                <div
                    id={item.id}
                    key={item.id}
                    className="benefits-list--item text-[#737373] text-left"
                >
                    <Icon icon={item.icon} className="mb-4 w-10 h-10 my-2" />
                    <h3 className="text-xl mb-2 font-medium text-black">
                        {item.title}
                    </h3>
                    <p>{item.content}</p>
                    <o className="flex">
                        <Icon
                            icon="solar:star-bold"
                            className="h-10 mr-1 text-secondary-500"
                        />
                        <Icon
                            icon="solar:star-bold"
                            className="h-10 mr-1 text-secondary-500"
                        />
                        <Icon
                            icon="solar:star-bold"
                            className="h-10 mr-1 text-secondary-500"
                        />
                        <Icon
                            icon="solar:star-bold"
                            className="h-10 mr-1 text-secondary-500"
                        />
                        <Icon
                            icon="solar:star-bold"
                            className="h-10 mr-1 text-secondary-500"
                        />
                    </o>
                </div>
            ))}
        </SectionContainer>
    );
};
