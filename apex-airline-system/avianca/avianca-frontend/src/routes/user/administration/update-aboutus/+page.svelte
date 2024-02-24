<script lang="ts">
import { writable } from 'svelte/store';
export let form; 
export let data; 


let slogan = data.aboutus.slogan;
let gif = data.aboutus.gif;
let yt = data.aboutus.yt;
let cards = data.aboutus.cards_amoun;

</script>

<h1 class="text-xl font-bold mb-8">About US - Information</h1>
<div class="flex justify-center items-center">
  <div class="md:w-full w-2/3">
    <form method="POST">
      <div>
        <label
          class="text-gray-800 font-semibold block my-3 text-md"
          for="slogan">Slogan</label
        >
        <input
          class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
          type="text"
          name="slogan"
          id="slogan"
          placeholder={data.aboutus.slogan}
          bind:value={slogan}
        />
      </div>
      <div>
        <label
          class="text-gray-800 font-semibold block my-3 text-md"
          for="gif">Gif along left part of the slogan</label
        >
        <input
          class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
          type="text"
          name="gif"
          id="gif"
          placeholder={data.aboutus.gif}
          bind:value={gif}
        />
      </div>
      <div>
        <label
          class="text-gray-800 font-semibold block my-3 text-md"
          for="yt">Youtube video</label
        >
        <input
          class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
          type="text"
          name="yt"
          id="yt"
          placeholder={data.aboutus.yt}
          bind:value={yt}
        />
      </div>
      <div>
        <label
          class="text-gray-800 font-semibold block my-3 text-md"
          for="cardsamount">Cards amount</label
        >
        <input
          class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
          type="number"
          name="cardsamount"
          id="cardsamount"
          placeholder={"Actual amount: " + data.aboutus.cards_amoun}
          bind:value={cards}
        />
      </div>
      <button
        type="submit"
        class="w-full mt-6 bg-indigo-600 rounded-lg px-4 py-2 text-lg text-white tracking-wide font-semibold font-sans"
        >Update</button
      >
      <p>{form?.message || " "}</p>
    </form>
  </div>
</div>
