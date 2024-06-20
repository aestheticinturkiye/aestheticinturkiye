package com.dev.esthomy.constants;
public class EmailTemplates {
    public static final String WELCOME_MESSAGE =
            "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "<meta charset=\"UTF-8\">\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "<title>Welcome to the Esthomy Family!</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "  <div style=\"font-family: Arial, sans-serif; font-size: 14px; line-height: 1.6;\">\n" +
                    "    <h2>Welcome to the Esthomy Family!</h2>\n" +
                    "    <p>Dear [Name],</p>\n" +
                    "    <p>We are thrilled to welcome you to the Esthomy family! Your registration has been successfully completed, and you are now a member of the Esthomy community.</p>\n" +
                    "    <p>Esthomy is a community dedicated to sharing the latest innovations and information in the field of aesthetics and beauty. We are excited to have a valuable member like you join us.</p>\n" +
                    "    <p>Within our community, you will have access to current information on beauty trends, expert recommendations, and a platform to share your experiences.</p>\n" +
                    "    <p>If you have any questions or feedback, please feel free to reach out to us. We are here to assist you.</p>\n" +
                    "    <p>Once again, welcome to the Esthomy family!</p>\n" +
                    "    <p>Best Regards,</p>\n" +
                    "    <p>[Your Name]</p>\n" +
                    "    <p>[Your Title]</p>\n" +
                    "    <p>[Your Hospital/Clinic Name]</p>\n" +
                    "    <p>[Your Contact Information]</p>\n" +
                    "    <p>[Your Email Address]</p>\n" +
                    "    <p>[Website URL]</p>\n" +
                    "  </div>\n" +
                    "</body>\n" +
                    "</html>";

    public static final String RECEIPT_MESSAGE =
            "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "<meta charset=\"UTF-8\">\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "<title>Receipt of Your Request for a Cosmetic Surgery Proposal</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "  <div style=\"font-family: Arial, sans-serif; font-size: 14px; line-height: 1.6;\">\n" +
                    "    <h2>Receipt of Your Request for a Cosmetic Surgery Proposal</h2>\n" +
                    "    <p>Dear [Name],</p>\n" +
                    "    <p>I hope this message finds you well. I wanted to inform you that we have received your request for a cosmetic surgery proposal, and I'm delighted to let you know that a personalized proposal has been prepared for you.</p>\n" +
                    "    <p>We have partnered with [Hospital/Clinic Name], a leading establishment renowned for its excellence in cosmetic procedures, to provide you with the best possible options tailored to your needs.</p>\n" +
                    "    <p>You can access the detailed proposal and further information regarding the suggested procedures by visiting the following link:</p>\n" +
                    "    <p><a href=\"[Website Link]\" style=\"color: #007bff;\">Click here to view the proposal</a></p>\n" +
                    "    <p>Once on the website, you'll find comprehensive details about:</p>\n" +
                    "    <ul>\n" +
                    "      <li>The recommended surgical procedure along with a detailed explanation.</li>\n" +
                    "      <li>Estimated duration of the surgery and the subsequent recovery period.</li>\n" +
                    "      <li>Total cost of the procedure and the available payment options.</li>\n" +
                    "      <li>Profiles of our skilled surgeons and expert medical team.</li>\n" +
                    "      <li>Testimonials from previous patients and visual documentation of their transformations.</li>\n" +
                    "    </ul>\n" +
                    "    <p>Should you have any inquiries or require additional information, please don't hesitate to reach out to us. Our team is dedicated to assisting you throughout this process.</p>\n" +
                    "    <p>We're excited about the opportunity to collaborate with you and help you achieve your desired aesthetic goals.</p>\n" +
                    "    <p>Warm regards,</p>\n" +
                    "    <p>[Your Name]</p>\n" +
                    "    <p>[Your Title]</p>\n" +
                    "    <p>[Your Hospital/Clinic Name]</p>\n" +
                    "    <p>[Your Contact Information]</p>\n" +
                    "    <p>[Your Email Address]</p>\n" +
                    "    <p>[Website URL]</p>\n" +
                    "  </div>\n" +
                    "</body>\n" +
                    "</html>";


    public static final String SUB_MAIL = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Thank You for Subscribing</title>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: Arial, sans-serif;\n" +
            "            background-color: #e0dcd7;\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "        }\n" +
            "        .container {\n" +
            "            max-width: 600px;\n" +
            "            margin: 0 auto;\n" +
            "            background-color: #ffffff;\n" +
            "            padding: 20px;\n" +
            "            border-radius: 8px;\n" +
            "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n" +
            "            position: relative;\n" +
            "        }\n" +
            "        .content {\n" +
            "            text-align: center;\n" +
            "            padding: 20px;\n" +
            "        }\n" +
            "        .content h1 {\n" +
            "            color: #333333;\n" +
            "        }\n" +
            "        .content p {\n" +
            "            color: #666666;\n" +
            "        }\n" +
            "        .button {\n" +
            "            text-align: center;\n" +
            "            padding: 20px;\n" +
            "        }\n" +
            "        .button a {\n" +
            "            background-color: #007BFF;\n" +
            "            color: #ffffff;\n" +
            "            padding: 10px 20px;\n" +
            "            text-decoration: none;\n" +
            "            border-radius: 5px;\n" +
            "        }\n" +
            "        .footer {\n" +
            "            text-align: center;\n" +
            "            padding: 20px;\n" +
            "            color: #999999;\n" +
            "            font-size: 12px;\n" +
            "            position: relative;\n" +
            "        }\n" +
            "        .footer img {\n" +
            "            position: absolute;\n" +
            "            bottom: 10px;\n" +
            "            right: 10px;\n" +
            "            width: 100px;\n" +
            "            height: auto;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"container\">\n" +
            "        <div class=\"content\">\n" +
            "            <h1>Thank You for Subscribing!</h1>\n" +
            "            <p>Dear [Name],</p>\n" +
            "            <p>Thank you for subscribing to Esthomy! We are thrilled to have you on board. Stay tuned for the latest updates, exclusive offers, and insights into our aesthetic services.</p>\n" +
            "            <p>If you have any questions or need assistance, feel free to reach out to our support team.</p>\n" +
            "        </div>\n" +
            "        <div class=\"button\">\n" +
            "            <a href=\"https://www.esthomy.com\" target=\"_blank\">Visit Our Website</a>\n" +
            "        </div>\n" +
            "        <div class=\"footer\">\n" +
            "            <p>&copy; 2024 Esthomy. All rights reserved.</p>\n" +
            "            <p>Esthomy, Your Trusted Partner in Aesthetic Operations.</p>\n" +
            "            <img src=\"esthomy.png\" alt=\"Esthomy Logo\">\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>\n";

}

