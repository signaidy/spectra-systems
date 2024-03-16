<script lang="ts">
    import { MoveLeft } from "lucide-svelte";
    export let form;
    export let data;
  
    let T1 = data.footer.Title_1
    let S1 = data.footer.Section_1
    let L1 = data.footer.L1
    let S2 = data.footer.Section_2
    let L2 = data.footer.L2
    let S3 = data.footer.Section_3
    let L3 = data.footer.L3
    let S4 = data.footer.Section_4
    let L4 = data.footer.L4
    let S5 = data.footer.Section_5
    let L5 = data.footer.L5
    let S6 = data.footer.Section_6
    let L6 = data.footer.L6
    let T2 = data.footer.Title_2
    let Q1 = data.footer.Q_1
    let LQ1 = data.footer.Link_quick_1
    let Q2 = data.footer.Q_2
    let LQ2 = data.footer.Linkl_quick_2
    let T3 = data.footer.Title_3
    let C1 = data.footer.C_1
    let C2 = data.footer.C_2
    let copycontent = data.footer.Copyright
  </script>
  
  <div class="arrowd">
  <a href="/user/administration/settings"><MoveLeft /></a>
  <h1 class="text-xl font-bold mb-3 mt-2">Footer - Information</h1>
  </div>
  <div class="flex justify-center items-center">
    <div class="md:w-full w-2/3">
      <form method="POST">
        <!-- Section 1 - About Us -->
        <div>
          <label
            class="text-indigo-800 font-semibold block my-3 text-md"
            for="slogan">Section 1 - Title</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="title1"
            id="title1"
            placeholder={T1}
            bind:value={T1}
          />
        </div>
        <div>
            <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
              >First value content</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="contentsection1"
              id="contentsection1"
              placeholder={S1}
              bind:value={S1}
            />
          </div>
          <div>
            <label class="text-gray-800 font-semibold block my-3 text-md" for="yt"
              >Link to first content</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="link_1"
              id="link_1"
              placeholder={L1}
              bind:value={L1}
            />
          </div>
        <div>
          <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
            >Second value content</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="contentsection2"
            id="contentsection2"
            placeholder={S2}
            bind:value={S2}
          />
        </div>
        <div>
          <label class="text-gray-800 font-semibold block my-3 text-md" for="yt"
            >Link to second content</label
          >
          <input
            class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
            type="text"
            name="link_2"
            id="link_2"
            placeholder={L2}
            bind:value={L2}
          />
        </div>
        <div>
            <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
              >Third value content</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="contentsection3"
              id="contentsection3"
              placeholder={S3}
              bind:value={S3}
            />
          </div>
          <div>
            <label class="text-gray-800 font-semibold block my-3 text-md" for="yt"
              >Link to Third content</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="link_3"
              id="link_3"
              placeholder={L3}
              bind:value={L3}
            />
          </div>
          <div>
            <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
              >Four value content</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="contentsection4"
              id="contentsection4"
              placeholder={S4}
              bind:value={S4}
            />
          </div>
          <div>
            <label class="text-gray-800 font-semibold block my-3 text-md" for="yt"
              >Link to four content</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="link_4"
              id="link_4"
              placeholder={L4}
              bind:value={L4}
            />
          </div>
          <div>
            <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
              >Five value content</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="contentsection5"
              id="contentsection5"
              placeholder={S5}
              bind:value={S5}
            />
          </div>
          <div>
            <label class="text-gray-800 font-semibold block my-3 text-md" for="yt"
              >Link to five content</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="link_5"
              id="link_5"
              placeholder={L5}
              bind:value={L5}
            />
          </div>
          <div>
            <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
              >Six value content</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="contentsection6"
              id="contentsection6"
              placeholder={S6}
              bind:value={S6}
            />
          </div>
          <div>
            <label class="text-gray-800 font-semibold block my-3 text-md" for="yt"
              >Link to six content</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="link_6"
              id="link_6"
              placeholder={L6}
              bind:value={L6}
            />
          </div>
          <!-- Section 2 - Quick Links -->
          <div>
            <label
              class="text-indigo-800 font-semibold block my-3 text-md"
              for="slogan">Section 2 - Title</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="title2"
              id="title2"
              placeholder={T2}
              bind:value={T2}
            />
          </div>
          <div>
              <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
                >First value content</label
              >
              <input
                class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
                type="text"
                name="conten1"
                id="conten1"
                placeholder={Q1}
                bind:value={Q1}
              />
            </div>
            <div>
              <label class="text-gray-800 font-semibold block my-3 text-md" for="yt"
                >Link to first content</label
              >
              <input
                class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
                type="text"
                name="quicklink_1"
                id="quicklink_1"
                placeholder={LQ1}
                bind:value={LQ1}
              />
            </div>
            <div>
                <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
                  >Second value content</label
                >
                <input
                  class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
                  type="text"
                  name="conten2"
                  id="conten2"
                  placeholder={Q2}
                  bind:value={Q2}
                />
              </div>
              <div>
                <label class="text-gray-800 font-semibold block my-3 text-md" for="yt"
                  >Link to second content</label
                >
                <input
                  class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
                  type="text"
                  name="quicklink_2"
                  id="quicklink_2"
                  placeholder={LQ2}
                  bind:value={LQ2}
                />
              </div>
               <!-- Section 3 - Contact Us -->
          <div>
            <label
              class="text-indigo-800 font-semibold block my-3 text-md"
              for="slogan">Section 3 - Title</label
            >
            <input
              class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
              type="text"
              name="title3"
              id="title3"
              placeholder={T3}
              bind:value={T3}
            />
          </div>
          <div>
              <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
                >First value content</label
              >
              <input
                class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
                type="text"
                name="contactus1"
                id="contactus1"
                placeholder={C1}
                bind:value={C1}
              />
            </div>
            <div>
                <label class="text-gray-800 font-semibold block my-3 text-md" for="gif"
                  >Second value content</label
                >
                <input
                  class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
                  type="text"
                  name="contactus2"
                  id="contactus2"
                  placeholder={C2}
                  bind:value={C2}
                />
              </div>
              <div>
                <label
                  class="text-indigo-800 font-semibold block my-3 text-md"
                  for="slogan">Copyright Text</label
                >
                <input
                  class="w-full bg-gray-100 px-4 py-2 rounded-lg focus:outline-none"
                  type="text"
                  name="copyright"
                  id="copyright"
                  placeholder={copycontent}
                  bind:value={copycontent}
                />
              </div>
              
      </form>
    </div>
  </div>
  
  <style>
    .arrowd{
      margin-top: 20px;
    }
  </style>