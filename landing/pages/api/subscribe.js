// pages/api/subscribe.js

export default async function handler(req, res) {
    if (req.method === "POST") {
        const { email } = req.body;

        try {
            const response = await fetch("http://localhost:8080/subscribe", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ email })
            });

            if (response.ok) {
                res.status(200).json({ message: "Subscription successful!" });
            } else {
                const errorData = await response.json();
                res.status(response.status).json({
                    message: errorData.message || "Subscription failed."
                });
            }
        } catch (error) {
            res.status(500).json({ message: "Subscription failed.", error });
        }
    } else {
        res.setHeader("Allow", ["POST"]);
        res.status(405).end(`Method ${req.method} Not Allowed`);
    }
}
