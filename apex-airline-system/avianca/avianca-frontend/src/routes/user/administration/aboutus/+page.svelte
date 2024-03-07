<script lang="ts">
  import { MoveLeft } from "lucide-svelte";
  export let form;
  export let data;

  let slogan = data.aboutus.slogan;
  let gif = data.aboutus.gif;
  let yt = data.aboutus.yt;
  let cards = data.aboutus.cards_amoun;
  let title_one = data.aboutus.title_one;
  let text_one = data.aboutus.text_one;
  let image_one = data.aboutus.img_one;
  let title_two = data.aboutus.title_two;
  let text_two = data.aboutus.text_two;
  let image_two = data.aboutus.img_two;
  let title_three = data.aboutus.title_three;
  let text_three = data.aboutus.text_three;
  let image_three = data.aboutus.img_three;
  let title_four = data.aboutus.title_four;
  let text_four = data.aboutus.text_four;
  let image_four = data.aboutus.img_four;
</script>

<div class="arrowd">
<a href="/user/administration"><MoveLeft /></a>
<h1 class="text-xl font-bold mb-3 mt-2">About US - Information</h1>
</div>
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
        <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
          >Gif along left part of the slogan</label
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
        <label class="text-gray-800 font-semibold block my-3 text-md" for="yt"
          >Youtube video</label
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
      {#if cards >= 1}
        <div>
          <label class="text-gray-800 font-semibold block my-3 text-md" for="t1"
            >First Card - Title</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="t1"
            id="t1"
            placeholder={data.aboutus.title_one}
            bind:value={title_one}
          />
        </div>
        <div>
          <label
            class="text-gray-800 font-semibold block my-3 text-md"
            for="tx1"
            >First Card - Text
          </label>
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="tx1"
            id="tx1"
            placeholder={data.aboutus.text_one}
            bind:value={text_one}
          />
        </div>
        <div>
          <label
            class="text-gray-800 font-semibold block my-3 text-md"
            for="img1">First Card - Image</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="img1"
            id="img1"
            placeholder={data.aboutus.img_one}
            bind:value={image_one}
          />
        </div>
      {/if}
      {#if cards >= 2}
        <div>
          <label class="text-gray-800 font-semibold block my-3 text-md" for="t2"
            >Second Card - Title</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="t2"
            id="t2"
            placeholder={data.aboutus.title_two}
            bind:value={title_two}
          />
        </div>
        <div>
          <label
            class="text-gray-800 font-semibold block my-3 text-md"
            for="tx2">Second Card - Text</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="tx2"
            id="tx2"
            placeholder={data.aboutus.text_two}
            bind:value={text_two}
          />
        </div>
        <div>
          <label
            class="text-gray-800 font-semibold block my-3 text-md"
            for="img2">Second Card - Image</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="img2"
            id="img2"
            placeholder={data.aboutus.img_two}
            bind:value={image_two}
          />
        </div>
      {/if}
      {#if cards >= 3}
        <div>
          <label class="text-gray-800 font-semibold block my-3 text-md" for="t3"
            >Third Card - Title</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="t3"
            id="t3"
            placeholder={data.aboutus.title_three}
            bind:value={title_three}
          />
        </div>
        <div>
          <label
            class="text-gray-800 font-semibold block my-3 text-md"
            for="tx3">Third Card - Text</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="tx3"
            id="tx3"
            placeholder={data.aboutus.text_three}
            bind:value={text_three}
          />
        </div>
        <div>
          <label
            class="text-gray-800 font-semibold block my-3 text-md"
            for="img3">Third Card - Image</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="img3"
            id="img3"
            placeholder={data.aboutus.img_three}
            bind:value={image_three}
          />
        </div>
        {/if}
        {#if cards >= 4}
        <div>
          <label class="text-gray-800 font-semibold block my-3 text-md" for="t4"
            >Four Card - Title</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="t4"
            id="t4"
            placeholder={data.aboutus.title_four}
            bind:value={title_four}
          />
        </div>
        <div>
          <label
            class="text-gray-800 font-semibold block my-3 text-md"
            for="tx4">Four Card - Text</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="tx4"
            id="tx4"
            placeholder={data.aboutus.text_four}
            bind:value={text_four}
          />
        </div>
        <div>
          <label
            class="text-gray-800 font-semibold block my-3 text-md"
            for="img4">Four Card - Image</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="img4"
            id="img4"
            placeholder={data.aboutus.img_four}
            bind:value={image_four}
          />
        </div>
      {/if}
      <button
        type="submit"
        class="w-full mt-6 bg-indigo-600 rounded-lg px-4 py-2 text-lg text-white tracking-wide font-semibold font-sans"
        >Update</button
      >
      <p>{form?.message || " "}</p>
    </form>
  </div>
</div>

<style>
  .arrowd{
    margin-top: 20px;
  }
</style>