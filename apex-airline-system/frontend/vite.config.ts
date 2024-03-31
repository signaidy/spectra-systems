import { sveltekit } from "@sveltejs/kit/vite";
import { defineConfig } from "vite";

export default defineConfig({
  server: {
    port: 3000,
  },
  preview: {
    host: true,
    port: 3000,
  },
  plugins: [sveltekit()],
});
