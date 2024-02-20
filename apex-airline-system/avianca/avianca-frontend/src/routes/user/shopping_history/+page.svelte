<script>
  import { onMount } from "svelte";
  import { Banknote } from "lucide-svelte";
  import aviancalogo from "$lib/assets/Avianca-Ticket-logo.png";
  import { jsPDF } from "jspdf";

  export let data;
  let userid = data.user.userId;

  
function generatePDF() {
const doc = new jsPDF();
doc.text("Hello world!", 10, 10);
doc.save("a4.pdf");
}

</script>

<h1 class="text-xl font-bold mb-8">Historical purchases</h1>

<div>
  <div class="p-10">
    <div
      class="max-w-full bg-white flex flex-col rounded overflow-hidden shadow-lg"
    >
      <div class="flex flex-row items-baseline flex-nowrap bg-gray-100 p-2">
        <h1 class="ml-0 uppercase font-bold text-black-500">Flight</h1>
        <p class="ml-0 font-normal text-gray-500">----</p>
        <h1 class="ml-0 uppercase font-bold text-black-500">Ticket</h1>
        <p class="ml-0 font-normal text-gray-500">----</p>
        <h1 class="text-grey-700 statef">Purchase date: 17-02-2024</h1>
      </div>
      <div class="mt-2 flex justify-start bg-white p-2">
        <div
          class="flex mx-2 ml-6 h8 px-2 flex-row items-baseline rounded-full bg-gray-100 p-1"
        >
          <div class="reduct"><Banknote /></div>
          <p class="font-normal text-sm ml-1 text-gray-500">First class</p>
        </div>
      </div>
      <div class="mt-2 flex sm:flex-row mx-6 sm:justify-between flex-wrap">
        <div class="flex flex-row place-items-center p-2">
          <img
            alt="Qatar Airways"
            class="w-20 h-20"
            src={aviancalogo}
            style="opacity: 1; transform-origin: 0% 50% 0px; transform: none;"
          />
          <div class="flex flex-col ml-2 mx-2">
            <!-- <p class="text-s text-black-500 font-bold">Avianca</p> -->
            <p class="text-xs text-gray-500">Departure date</p>
            <p>sdfsdfdsf</p>
            <p class="text-xs text-gray-500">Arrival date</p>
            <p>sdfsdfsdfsdfsdf</p>
          </div>
        </div>

        <div class="flex flex-col p-2">
          <p class="text-black-500">
            <span class="font-bold">Origin:</span>
          </p>
          <p class="text-gray-500">sdfdsfdsf</p>
        </div>
        <div class="flex flex-col flex-wrap p-2">
          <p class="text-black-500">
            <span class="font-bold">Destination</span>
          </p>
          <p class="text-gray-500">sdfsdfdsfdsf</p>
        </div>
        <div class="flex flex-col flex-wrap p-2">
          <p class="text-black-500">
            <span class="font-bold">Price</span>
          </p>
          <p class="text-gray-500">aaaaaa</p>
        </div>
      </div>
      <button
        class="middle none center  rounded-lg bg-blue-500 py-3 px-6 font-sans text-xs font-bold uppercase text-white shadow-md shadow-blue-500/20 transition-all hover:shadow-lg hover:shadow-blue-500/40 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
        on:click={generatePDF}
        data-ripple-light="true">
        Generate PDF
      </button>
    </div>
  </div>
</div>

<style>
  .reduct {
    height: 17px;
    width: 25px;
    color: grey;
  }

  .statef {
    margin-left: 740px;
  }
</style>
