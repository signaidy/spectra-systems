# Usamos Node LTS
FROM node:20

WORKDIR /usr/src/app

ARG PUBLIC_BACKEND_URL
ENV PUBLIC_BACKEND_URL=${PUBLIC_BACKEND_URL}

# (optional) make sure prod optimizations kick in
ENV NODE_ENV=production

# Copiamos el código
COPY . .

# Instalar deps y construir
RUN --mount=type=cache,target=/root/.npm npm ci
RUN npm run build

EXPOSE 4173
CMD ["npm", "run", "preview", "--", "--host", "0.0.0.0", "--port", "4173"]