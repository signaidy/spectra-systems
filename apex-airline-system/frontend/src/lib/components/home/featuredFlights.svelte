<script lang="ts">
  import background from "$lib/assets/background.jpg";
  import { onMount } from "svelte";

  let FeatureFlight1; 
  let FlightCountry1;
  let Content1; 
  let FeatureFlight2; 
  let FlightCountry2;
  let Content2;
  let FeatureFlight3; 
  let FlightCountry3;
  let Content3;

  onMount(async () => {
    fetch("http://localhost:8080/home")
      .then((response) => response.json())
      .then((homeinformation) => {
        FeatureFlight1 = homeinformation.FeatureImage1;
        FlightCountry1 = homeinformation.Title1;
        Content1 = homeinformation.Content1; 
        FeatureFlight2 = homeinformation.FeatureImage2;
        FlightCountry2 = homeinformation.Title2;
        Content2 = homeinformation.Content2; 
        FeatureFlight3 = homeinformation.FeatureImage3;
        FlightCountry3 = homeinformation.Title3;
        Content3 = homeinformation.Content3; 
        return homeinformation;
      });
  });
</script>

<div class="flex flex-col container p-8 gap-y-5">
  <div class="font-bold text-2xl text-center">Featured Flights</div>
  <div class="grid grid-cols-3 gap-x-10">
    <div class="image-container">
      <img
        src={FeatureFlight1}
        class="bg-primary h-60 w-[584px] rounded-lg object-cover"
        alt="Paris Background"
      />
      <p class="text-sm mt-2 font-bold text-center">{FlightCountry1}</p>
      <br />
      <p class="text-sm">{Content1}</p>
    </div>
    <div class="image-container">
      <img
        src={FeatureFlight2}
        class="bg-primary h-60 w-[584px] rounded-lg object-cover"
        alt="Paris Background"
      />
      <p class="text-sm mt-2 font-bold text-center">{FlightCountry2}</p>
      <br />
      <p class="text-sm">{Content2}</p>
    </div>
    <div class="image-container">
      <img
        src={FeatureFlight3}
        class="bg-primary h-60 w-[584px] rounded-lg object-cover"
        alt="Paris Background"
      />
      <p class="text-sm mt-2 font-bold text-center">{FlightCountry3}</p>
      <br />
      <p class="text-sm">{Content3}</p>
    </div>
  </div>
</div>
