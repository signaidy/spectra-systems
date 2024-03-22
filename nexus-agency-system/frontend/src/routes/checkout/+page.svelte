<script>
  import { MoveLeft } from "lucide-svelte";
  import avianca from "$lib/assets/Avianca-Ticket-logo.png";
  import visaImage from "$lib/assets/visa.png";
  import mastercardImage from "$lib/assets/mastercard.png";
  import { page } from "$app/stores";
  import { onMount } from "svelte";
  export let data;

  export let isOpen = false;

  let userid = data.user.userId;

  const flight_id =  $page.url.searchParams.get('flight_id');
  let passengers = $page.url.searchParams.get("passengers");
  let category = $page.url.searchParams.get("category");

  let card = "";

  let availabletickets = [];
  let ticketsamount_available;

  let price;
  let from;
  let to;
  let state = "active";

  async function handlePayNow() {
    if (ticketsamount_available < passengers) {
      alert(
        "Insufficient tickets available. Please select a lower number of passengers."
      );
      return;
    }
    isOpen = true; 
  }

  onMount(async () => {
    fetch(`http://localhost:8080/availabletickets/${flight_id}/${category}`)
      .then((response) => response.json())
      .then((available) => {
        availabletickets = available;
        if (availabletickets.length > 0) {
          price = availabletickets[0].price;
          from = availabletickets[0].origin;
          to = availabletickets[0].destination;
        }
      });
  });

  onMount(async () => {
    const response = await fetch(
      `http://localhost:8080/ticketsamount/${flight_id}/${category}`
    );
    const data = await response.json();
    ticketsamount_available = data.tickets_amount;
  });
</script>

{#if isOpen}
<div class="fixed left-0 top-0 flex h-full w-full items-center justify-center bg-black bg-opacity-50 py-10">
  <div class="max-h-full w-full max-w-xl overflow-y-auto sm:rounded-2xl bg-white">
    <div class="w-full">
      <div class="m-8 my-20 max-w-[400px] mx-auto">
        <div class="mb-8">
          <h1 class="mb-4 text-3xl font-extrabold">Thank you for your purchase</h1>
          <p class="text-gray-600">Now you can decide to continue searching for other travels or go to see your receipt</p>
        </div>
        <div class="space-y-4">
          <a href="/" class="p-3 bg-black rounded-full text-white w-full font-semibold">Continue buying</a>
          <a href="user/shopping_history" class="p-3 bg-white border rounded-full w-full font-semibold">Go check your receipt</a>
        </div>
      </div>
    </div>
  </div>
</div>
{/if}


<form method="POST">
  <div class="min-w-screen min-h-screen bg-gray-50 py-3">
    <div class="px-5">
      <div class="mb-0">
        <a
          href="/"
          class="focus:outline-none hover:underline text-gray-500 text-sm"
          ><MoveLeft /></a
        >
      </div>
      <div class="mb-2">
        <h1 class="text-3xl md:text-5xl font-bold text-gray-600">Checkout</h1>
      </div>
      <div class="mb-5 text-gray-400">
        <a href="/" class="focus:outline-none hover:underline text-gray-500"
          >Home</a
        >
        /
        <a href="#" class="focus:outline-none hover:underline text-gray-500"
          >Cart</a
        >
        / <span class="text-gray-600">Checkout</span>
      </div>
    </div>
    <div
      class="w-full bg-white border-t border-b border-gray-200 px-5 py-10 text-gray-800"
    >
      <div class="w-full">
        <div class="-mx-3 md:flex items-start">
          <div class="px-3 md:w-7/12 lg:pr-10">
            <div
              class="w-full mx-auto text-gray-800 font-light mb-6 border-b border-gray-200 pb-6"
            >
              <div class="w-full flex items-center">
                <div
                  class="overflow-hidden rounded-lg w-16 h-16 bg-gray-50 border border-gray-200"
                >
                  <img src={avianca} alt="" />
                </div>
                <div class="flex-grow pl-3">
                  <h6 class="font-semibold uppercase text-gray-600">
                    {category} Flight
                  </h6>
                  <p class="text-gray-400">x {passengers}</p>
                </div>
                <div>
                  <span class="font-semibold text-gray-600 text-xl"
                    >${price * passengers}</span
                  ><span class="font-semibold text-gray-600 text-sm">.00</span>
                </div>
              </div>
            </div>
            <div class="mb-6 pb-6 border-b border-gray-200 text-gray-800">
              <div class="w-full flex mb-3 items-center">
                <div class="flex-grow">
                  <span class="text-gray-600">Unitary price</span>
                </div>
                <div class="pl-3">
                  <span class="font-semibold">${price}.00</span>
                </div>
              </div>
              <div class="w-full flex items-center">
                <div class="flex-grow">
                  <span class="text-gray-600">Discount</span>
                </div>
                <div class="pl-3">
                  <span class="font-semibold">$00.00</span>
                </div>
              </div>
            </div>
            <div
              class="mb-6 pb-6 border-b border-gray-200 md:border-none text-gray-800 text-xl"
            >
              <div class="w-full flex items-center">
                <div class="flex-grow">
                  <span class="text-gray-600">Total</span>
                </div>
                <div class="pl-3">
                  <span class="font-semibold">${price * passengers}.00</span>
                </div>
              </div>
            </div>
          </div>
          <div class="px-3 md:w-5/12">
            <div
              class="w-full mx-auto rounded-lg bg-white border border-gray-200 p-3 text-gray-800 font-light mb-6"
            >
              <div class="w-full flex mb-3 items-center">
                <div class="w-32">
                  <span class="text-gray-600 font-semibold">Leaving from:</span>
                </div>
                <div class="flex-grow pl-3">
                  <span>{from}</span>
                </div>
              </div>
              <div class="w-full flex items-center">
                <div class="w-32">
                  <span class="text-gray-600 font-semibold">Going to: </span>
                </div>
                <div class="flex-grow pl-3">
                  <span>{to}</span>
                </div>
              </div>
            </div>
            <div
              class="w-full mx-auto rounded-lg bg-white border border-gray-200 text-gray-800 font-light mb-6"
            >
              <div class="w-full p-3 border-b border-gray-200">
                <div class="mb-5">
                  <label for="type1" class="flex cursor-pointer items-center">
                    <input
                      on:click={() => (card = "Visa")}
                      type="radio"
                      value="visa"
                      class="form-radio h-5 w-5 text-indigo-500"
                      name="type"
                      id="type1"
                    />
                    <img src={visaImage} class="ml-3 h-12" />
                    <input
                      on:click={() => (card = "MasterCard")}
                      type="radio"
                      class="form-radio h-5 w-20 text-indigo-500"
                      value="mastercard"
                      name="type"
                      id="type1"
                    />
                    <img src={mastercardImage} class="ml-1 h-12" />
                  </label>
                </div>
                <div>
                  <div class="mb-3">
                    <label class="text-gray-600 font-semibold text-sm mb-2 ml-1"
                      >Name on card</label
                    >
                    <div>
                      <input
                        class="w-full px-3 py-2 mb-1 border border-gray-200 rounded-md focus:outline-none focus:border-indigo-500 transition-colors"
                        type="text"
                      />
                    </div>
                  </div>
                  <div class="mb-3">
                    <label class="text-gray-600 font-semibold text-sm mb-2 ml-1"
                      >Card number</label
                    >
                    <div>
                      <input
                        class="w-full px-3 py-2 mb-1 border border-gray-200 rounded-md focus:outline-none focus:border-indigo-500 transition-colors"
                        placeholder="0000-0000-0000-0000"
                        type="text"
                        required
                      />
                    </div>
                  </div>
                  <div class="mb-3 -mx-2 flex items-end">
                    <div class="px-2 w-1/4">
                      <label
                        class="text-gray-600 font-semibold text-sm mb-2 ml-1"
                        >Expiration date</label
                      >
                      <div>
                        <select
                          class="form-select w-full px-3 py-2 mb-1 border border-gray-200 rounded-md focus:outline-none focus:border-indigo-500 transition-colors cursor-pointer"
                        >
                          <option value="01">01 - January</option>
                          <option value="02">02 - February</option>
                          <option value="03">03 - March</option>
                          <option value="04">04 - April</option>
                          <option value="05">05 - May</option>
                          <option value="06">06 - June</option>
                          <option value="07">07 - July</option>
                          <option value="08">08 - August</option>
                          <option value="09">09 - September</option>
                          <option value="10">10 - October</option>
                          <option value="11">11 - November</option>
                          <option value="12">12 - December</option>
                        </select>
                      </div>
                    </div>
                    <div class="px-2 w-1/4">
                      <select
                        class="form-select w-full px-3 py-2 mb-1 border border-gray-200 rounded-md focus:outline-none focus:border-indigo-500 transition-colors cursor-pointer"
                      >
                        <option value="2020">2020</option>
                        <option value="2021">2021</option>
                        <option value="2022">2022</option>
                        <option value="2023">2023</option>
                        <option value="2024">2024</option>
                        <option value="2025">2025</option>
                        <option value="2026">2026</option>
                        <option value="2027">2027</option>
                        <option value="2028">2028</option>
                        <option value="2029">2029</option>
                      </select>
                    </div>
                    <div class="px-2 w-1/4">
                      <label
                        class="text-gray-600 font-semibold text-sm mb-2 ml-1"
                        >Security code</label
                      >
                      <div>
                        <input
                          class="w-full px-3 py-2 mb-1 border border-gray-200 rounded-md focus:outline-none focus:border-indigo-500 transition-colors"
                          placeholder="000"
                          type="text"
                          required
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div>
              <button
                class="block w-full max-w-xs mx-auto bg-indigo-500 hover:bg-indigo-700 focus:bg-indigo-700 text-white rounded-lg px-3 py-2 font-semibold"
                on:click={handlePayNow}
                ><i class="mdi mdi-lock-outline mr-1"></i>Pay Now</button
              >
            </div>
          </div>
        </div>
      </div>
    </div>
    <input type="hidden" name="user_id" value={userid} />
    <input type="hidden" name="paymenth_method" value={card} />
    <input type="hidden" name="passengers" value={passengers} />
    <input type="hidden" name="flightJson" value={flight_id} />
    <input type="hidden" name="category" value={category} />
    <input type="hidden" name="state" value={state} />
  </div>
</form>
