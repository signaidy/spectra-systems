<script lang="ts">
    import { MoveLeft } from "lucide-svelte";
    export let form;
    export let data;
  
    let Title = data.partners.Title;
    let Description = data.partners.Description;
    let Partner1 = data.partners.Partner1;
    let L1 = data.partners.L1;
    let Partner2 = data.partners.Partner2;
    let L2 = data.partners.L2;
    let Partner3 = data.partners.Partner3;
    let L3 = data.partners.L3;
    let Partner4 = data.partners.Partner4;
    let L4 = data.partners.L4;
    let Partner5 = data.partners.Partner5;
    let L5 = data.partners.L5; 
  </script>
  
  <div class="arrowd">
  <a href="/user/administration/settings"><MoveLeft /></a>
  <h1 class="text-xl font-bold mb-3 mt-2">Partners</h1>
  </div>
  <div class="flex justify-center items-center">
    <div class="md:w-full w-2/3">
      <form method="POST">
        <div>
          <label
            class="text-gray-800 font-semibold block my-3 text-md"
            for="slogan">Title</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="Title"
            id="Title"
            placeholder={data.partners.Title}
            bind:value={Title}
          />
        </div>
        <div>
            <label
              class="text-gray-800 font-semibold block my-3 text-md"
              for="slogan">Description</label
            >
            <textarea
              class="w-full bg-gray-100 px-4 py-4 rounded-lg focus:outline-none"
              type="text"
              name="Description"
              id="Description"
              placeholder={data.partners.Description}
              bind:value={Description}
            />
          </div>
        <div>
            <div class="flex">
                <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
                >First partner - </label
              >
              <img src={data.partners.Partner1} class="w-[70px] h-[25px] mt-3 mx-2">
            </div>
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="Partner1"
            id="Partner1"
            placeholder={data.partners.Partner1}
            bind:value={Partner1}
          />
        </div>
        <div>
          <label class="text-gray-800 font-semibold block my-3 text-md" for="yt"
            >Link to partner page</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="L1"
            id="L1"
            placeholder={data.partners.L1}
            bind:value={L1}
          />
        </div>
        <div>
            <div class="flex">
                <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
              >Second partner - </label
            >
            <img src={data.partners.Partner2} class="w-[70px] h-[25px] mt-3 mx-2">
            </div>
            
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="Partner2"
              id="Partner2"
              placeholder={data.partners.Partner2}
              bind:value={Partner2}
            />
          </div>
          <div>
            <label class="text-gray-800 font-semibold block my-3 text-md" for="yt"
              >Link to partner page</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="L2"
              id="L2"
              placeholder={data.partners.L2}
              bind:value={L2}
            />
          </div>
          <div>
            <div class="flex">
                <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
              >Third partner - </label
            >
            <img src={data.partners.Partner3} class="w-[70px] h-[25px] mt-3 mx-2">
            </div>
            
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="Partner3"
              id="Partner3"
              placeholder={data.partners.Partner3}
              bind:value={Partner3}
            />
          </div>
          <div>
            <label class="text-gray-800 font-semibold block my-3 text-md" for="yt"
              >Link to partner page</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="L3"
              id="L3"
              placeholder={data.partners.L3}
              bind:value={L3}
            />
          </div>
          <div>
            <div class="flex">
                <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
              >Fourth partner - </label
            >
            <img src={data.partners.Partner4} class="w-[70px] h-[25px] mt-3 mx-2">
            </div>
            
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="Partner4"
              id="Partner4"
              placeholder={data.partners.Partner4}
              bind:value={Partner4}
            />
          </div>
          <div>
            <label class="text-gray-800 font-semibold block my-3 text-md" for="yt"
              >Link to partner page</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="L4"
              id="L4"
              placeholder={data.partners.L4}
              bind:value={L4}
            />
          </div>
          <div>
            <div class="flex">
                <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
              >Fifth partner - </label
            >
            <img src={data.partners.Partner5} class="w-[70px] h-[25px] mt-3 mx-2">
            </div>
            
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="Partner5"
              id="Partner5"
              placeholder={data.partners.Partner5}
              bind:value={Partner5}
            />
          </div>
          <div>
            <label class="text-gray-800 font-semibold block my-3 text-md" for="yt"
              >Link to partner page</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="L5"
              id="L5"
              placeholder={data.partners.L5}
              bind:value={L5}
            />
          </div>
        <button
          type="submit"
          class="w-full mt-6 bg-indigo-600 rounded-lg px-4 py-2 text-lg text-white tracking-wide font-semibold font-sans"
          >Update</button
        >
      </form>
    </div>
  </div>
  
  <style>
    .arrowd{
      margin-top: 20px;
    }
  </style>