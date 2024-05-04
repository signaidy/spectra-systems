<script lang="ts">
    import { MoveLeft } from "lucide-svelte";
    export let form;
    export let data;
  </script>
  
  <div class="arrowd">
  <a href="/user/administration/settings"><MoveLeft /></a>
  <h1 class="text-xl font-bold mb-3 mt-2">Aliance- Insertion</h1>
  </div>
  <div class="flex justify-center items-center">
    <div class="md:w-full w-2/3">
      <form method="POST">
        <div>
          <label
            class="text-gray-800 font-semibold block my-3 text-md"
            for="IP">IP</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="IP"
            id="IP"
            placeholder={"Insert IP"}
          />
        </div>
        <div>
          <label class="text-gray-800 font-semibold block my-3 text-md" for="ENDPOINT"
            >ENDPOINT</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="endpoint"
            id="endpoint"
            placeholder={"Insert endpoint"}
          />
        </div>
        <div>
          <label class="text-gray-800 font-semibold block my-3 text-md" for="KEY"
            >Key</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="key"
            id="key"
            placeholder={"Insert key"}
          />
        </div>
        <button
          type="submit"
          class="w-full mt-6 bg-indigo-600 rounded-lg px-4 py-2 text-lg text-white tracking-wide font-semibold font-sans"
          >Insert Aliance</button
        >
      </form>
    </div>
  </div>
  
  <style>
    .arrowd{
      margin-top: 20px;
    }
  </style>