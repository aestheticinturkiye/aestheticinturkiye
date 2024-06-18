FROM --platform=linux/amd64 node:22.1-alpine

WORKDIR /app
COPY package*.json ./
RUN npm i
COPY . .
RUN npm run build
EXPOSE 5174
CMD [ "npm", "run", "preview" ]
