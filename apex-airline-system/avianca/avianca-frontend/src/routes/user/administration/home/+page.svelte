<script lang="ts">
  import { MoveLeft } from "lucide-svelte";
  export let form;
  export let data;

  let background = data.home.Background
  let FeatureFlight1 = data.home.FeatureImage1;
  let FlightCountry1 = data.home.Title1;
  let Content1 = data.home.Content1;
  let FeatureFlight2 = data.home.FeatureImage2;
  let FlightCountry2 = data.home.Title2;
  let Content2 = data.home.Content2;
  let FeatureFlight3 = data.home.FeatureImage3;
  let FlightCountry3 = data.home.Title3;
  let Content3 = data.home.Content3;
</script>

<div class="arrowd">
  <a href="/user/administration/settings"><MoveLeft /></a>
  <h1 class="text-xl font-bold mb-3 mt-2">Home - Information</h1>
</div>
<div class="flex justify-center items-center">
  <div class="md:w-full w-2/3">
    <form method="POST">
      <div>
        <label
          class="text-indigo-800 font-semibold block my-3 text-md"
          for="slogan">Background Image</label
        >
        <input
          class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
          type="text"
          name="background"
          id="background"
          placeholder={data.home.Background}
          bind:value={background}
        />
      </div>
      <div>
        <label
          class="text-indigo-800 font-semibold block my-3 text-md"
          for="slogan">First Featured Flight - Image</label
        >
        <input
          class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
          type="text"
          name="image1"
          id="image1"
          placeholder={data.home.FeatureImage1}
          bind:value={FeatureFlight1}
        />
      </div>
      <div>
        <label
          class="text-gray-800 font-semibold block my-3 text-md"
          for="slogan">First Featured Flight - Title</label
        >
        <input
          class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
          type="text"
          name="title1"
          id="title1"
          placeholder={data.home.Title1}
          bind:value={FlightCountry1}
        />
      </div>
      <div>
        <label
          class="text-gray-800 font-semibold block my-3 text-md"
          for="slogan">First Featured Flight - Content</label
        >
        <input
          class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
          type="text"
          name="content1"
          id="content1"
          placeholder={data.home.Content1}
          bind:value={Content1}
        />
      </div>
      <div>
        <label
          class="text-indigo-800 font-semibold block my-3 text-md"
          for="slogan">Second Featured Flight - Image</label
        >
        <input
          class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
          type="text"
          name="image2"
          id="image2"
          placeholder={data.home.FeatureImage2}
          bind:value={FeatureFlight2}
        />
      </div>
      <div>
        <label
          class="text-gray-800 font-semibold block my-3 text-md"
          for="slogan">Second Featured Flight - Title</label
        >
        <input
          class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
          type="text"
          name="title2"
          id="title2"
          placeholder={data.home.Title2}
          bind:value={FlightCountry2}
        />
      </div>
      <div>
        <label
          class="text-gray-800 font-semibold block my-3 text-md"
          for="slogan">Second Featured Flight - Content</label
        >
        <input
          class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
          type="text"
          name="content2"
          id="content2"
          placeholder={data.home.Content2}
          bind:value={Content2}
        />
      </div>
      <div>
        <label
          class="text-indigo-800 font-semibold block my-3 text-md"
          for="slogan">Third Featured Flight - Image</label
        >
        <input
          class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
          type="text"
          name="image3"
          id="image3"
          placeholder={data.home.FeatureImage3}
          bind:value={FeatureFlight3}
        />
      </div>
      <div>
        <label
          class="text-gray-800 font-semibold block my-3 text-md"
          for="slogan">Third Featured Flight - Title</label
        >
        <input
          class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
          type="text"
          name="title3"
          id="title3"
          placeholder={data.home.Title3}
          bind:value={FlightCountry3}
        />
      </div>
      <div>
        <label
          class="text-gray-800 font-semibold block my-3 text-md"
          for="slogan">Third Featured Flight - Content</label
        >
        <input
          class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
          type="text"
          name="content3"
          id="content3"
          placeholder={data.home.Content3}
          bind:value={Content3}
        />
      </div>
      <button
          type="submit"
          class="w-full mt-6 bg-indigo-600 rounded-lg px-4 py-2 text-lg text-white tracking-wide font-semibold font-sans"
          >Update</button>
    </form>
  </div>
</div>

<style>
  .arrowd {
    margin-top: 20px;
  }
</style>
