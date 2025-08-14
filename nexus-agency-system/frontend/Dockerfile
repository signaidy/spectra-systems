# ---------- build stage ----------
FROM node:20-bullseye AS build
WORKDIR /usr/src/app

# SvelteKit public env var (available at build time)
ARG PUBLIC_BACKEND_URL
ENV PUBLIC_BACKEND_URL=$PUBLIC_BACKEND_URL
# Install dependencies
COPY package.json package-lock.json ./
RUN npm ci --include=dev

COPY . .
RUN npm run build
FROM node:20-bullseye AS runtime
# Install only production dependencies
WORKDIR /usr/src/app
COPY --from=build /usr/src/app ./

EXPOSE 4173
CMD ["npm", "run", "preview", "--", "--host", "0.0.0.0", "--port", "4173"]