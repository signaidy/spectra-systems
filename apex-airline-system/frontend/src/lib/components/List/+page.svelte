<script lang="ts">
  import { onMount } from "svelte";
  import { PUBLIC_BASE_URL } from "$env/static/public";
  import { MoveLeft } from "lucide-svelte";

  export let data;
  console.log(data);
  let listinformation: any;

  let isModalOpen = false;

  function openModal() {
    isModalOpen = true; // Set here to open the modal
  }

  onMount(async () => {
    fetch(`${PUBLIC_BASE_URL}/searchdatatake`)
      .then((response) => response.json())
      .then((searches) => {
        listinformation = searches;
      });
  });

  function sendEmail() {
    const email = document.getElementById("email").value;

    let tableContent = "";

    //Headers
    tableContent += buildRow(Object.keys(listinformation[0]), true);

    tableContent += buildRow(
      Array(Object.keys(listinformation[0]).length).fill("-")
    );

    // Add data rows
    for (const item of listinformation) {
      tableContent += buildRow(Object.values(item));
    }

    function buildRow(rowData, isHeader = false) {
      let rowContent = "";
      for (const cell of rowData) {
        rowContent += `  ${cell}  |`;
      }
      return isHeader
        ? `| ${rowContent.slice(0, -2)}\n`
        : `|${rowContent.slice(0, -2)}\n`;
    }

    const emailBody = `This email contains search data in a text-based table format:\n\n${tableContent}`;

    Email.send({
      SecureToken: "11c78855-54e1-46a7-bc68-6751ad92e0dc",
      To: email,
      From: "systemspectracjm@gmail.com",
      Subject: "Searches spread sheet - Airline",
      Body: emailBody,
    });
  }

  function download() {
    let csvContent = "";

    //Headers
    csvContent += Object.keys(listinformation[0]).join(",") + "\n";

    //Rows of data
    for (const item of listinformation) {
      csvContent += Object.values(item).join(",") + "\n";
    }

    const csvBlob = new Blob([csvContent], { type: "text/csv;charset=utf-8" });
    const csvUrl = URL.createObjectURL(csvBlob);

    const link = document.createElement("a");
    link.href = csvUrl;
    link.download = "search_data.csv";
    link.click();
  }
</script>

<svelte:head>
  <script src="https://smtpjs.com/v3/smtp.js">
  </script>
</svelte:head>

{#if isModalOpen == true}
  <form on:submit={sendEmail}>
    <div
      class="fixed left-0 top-0 flex h-full w-full items-center justify-center bg-black bg-opacity-50 py-10"
    >
      <div
        class="max-h-full w-full max-w-xl overflow-y-auto sm:rounded-2xl bg-white"
      >
        <div class="flex mt-5 ml-10">
          <button on:click={() => (isModalOpen = false)}><MoveLeft /></button>
        </div>
        <div class="w-full">
          <div class="m-1 max-w-[400px] mx-auto">
            <div class="mb-8">
              <h1 class="mb-4 text-3xl font-extrabold">Electronic Sheet</h1>
              <p class="text-gray-600">
                Insert the email where do you want to send the spread sheet:
              </p>
            </div>
            <div class="flex-center items-centered">
              <input
                type="email"
                id="email"
                required
                style="width: 300px; padding: 0.5rem 1rem; border: 1px solid #ccc; border-radius: 4px; font-size: 1rem;"
              />
            </div>
            <div class="space-x-5 mt-10 flex">
              <button
                class="p-3 bg-red-600 rounded-full text-white w-full h-100 font-semibold"
                on:click={(isModalOpen = false)}>Cancel</button
              >
              <button
                class="p-3 bg-green-600 rounded-full text-white w-full font-semibold"
                type="submit">Send</button
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </form>
{/if}

<h2 class="text-3xl font-bold text-center">
  List of searches made in the website + {isModalOpen}
</h2>

{#if listinformation}
  <table class="table-auto w-full mt-10">
    <thead>
      <tr class="bg-gray-100 text-gray-600 uppercase">
        <th class="text-center">Origin</th>
        <th class="text-center">Destination</th>
        <th class="text-center">Departure Date</th>
        <th class="text-center">Return Date</th>
        <th class="text-center">Passengers</th>
        <th class="text-center">Flight Type</th>
        <th class="text-center">Type Search</th>
        <th class="text-center">Search made on</th>
      </tr>
    </thead>
    <tbody>
      {#each listinformation as list}
        <tr class="border-b hover:bg-gray-200">
          <td class="text-center">{list.Origin}</td>
          <td class="text-center">{list.Destination}</td>
          <td class="text-center">{list.Departure_date}</td>
          <td class="text-center">{list.Return_date}</td>
          <td class="text-center">{list.passengers}</td>
          <td class="text-center">{list.Flight_type}</td>
          <td class="text-center">{list.type_search}</td>
          <td class="text-center">{list.date_made}</td>
        </tr>
      {/each}
    </tbody>
  </table>
  <div class="flex justify-end mr-3">
    <button
      on:click={download}
      class="bg-indigo-500 text-white py-2 px-4 rounded shadow-sm hover:bg-blue-700 mt-5 flex-right"
    >
      Download
    </button>
    <button
      on:click={() => (isModalOpen = true)}
      class="bg-green-500 text-white py-2 px-4 rounded shadow-sm hover:bg-blue-700 mt-5 flex-right ml-5"
    >
      Export to CSV
    </button>
  </div>
{/if}
