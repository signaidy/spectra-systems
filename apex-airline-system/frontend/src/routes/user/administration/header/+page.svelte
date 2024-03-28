<script lang="ts">
    import { MoveLeft } from "lucide-svelte";
    export let form;
    export let data;
  
    let Text = data.head.Text_Logo;
    let Section = data.head.Section;
    let Link_Section = data.head.Link_Section;
    let Link_Profile = data.head.Link_Profile;
    let Link_Login = data.head.Link_Login;
    let Logo = data.head.Logo;
  </script>
  
  <div class="arrowd">
  <a href="/user/administration/settings"><MoveLeft /></a>
  <h1 class="text-xl font-bold mb-3 mt-2">Header - Information</h1>
  </div>
  <div class="flex justify-center items-center">
    <div class="md:w-full w-2/3">
      <form method="POST">
        <div>
          <label
            class="text-gray-800 font-semibold block my-3 text-md"
            for="slogan">Company name</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="Text_Logo"
            id="Text_Logo"
            placeholder={data.head.Text_Logo}
            bind:value={Text}
          />
        </div>
        <div>
            <div class="flex">
            <label
              class="text-gray-800 font-semibold block my-3 text-md"
              for="tx1"
              >Logo of the Company 
            </label>
            <img src={Logo} class="w-[30px] h-[25px] mt-3 mx-2">
            </div>
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="Logo"
              id="Logo"
              placeholder={data.head.Logo}
              bind:value={Logo}
            />
          </div>
        <div>
          <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
            >Middle Section of header</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="Section"
            id="Section"
            placeholder={data.head.Section}
            bind:value={Section}
          />
        </div>
        <div>
          <label class="text-gray-800 font-semibold block my-3 text-md" for="yt"
            >Link to Section</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="Link_Section"
            id="Link_Section"
            placeholder={data.head.Link_Section}
            bind:value={Link_Section}
          />
        </div>
        <div>
          <label
            class="text-gray-800 font-semibold block my-3 text-md"
            for="cardsamount">Link to Profile</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="Link_Profile"
            id="Link_Profile"
            placeholder={data.head.Link_Profile}
            bind:value={Link_Profile}
          />
        </div>
          <div>
            <label class="text-gray-800 font-semibold block my-3 text-md" for="t1"
              >Link to Login</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="Link_Login"
              id="Link_Login"
              placeholder={data.head.Link_Login}
              bind:value={Link_Login}
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
    .arrowd{
      margin-top: 20px;
    }
  </style>