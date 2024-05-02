<script lang="ts">
  import { Value } from "$lib/components/ui/select/index.js";
  import * as d3 from "d3";
  import { onMount } from "svelte";
  export let data;

  let margin, width, height;
  let selection;

  onMount(async () => {
    const information = await data.G2;
    const graph1 = await data.G1;
    const graph3 = await data.G3;
    console.log(graph3);

    //GRAPH1 (TYPE SEARCHES)------------------------------------------------------------------------------------------------------------------------------------------------
    //Creation of PIE Chart
    if (graph1.length > 0) {
      const pieData = graph1;

      const pieWidth = 500;
      const pieHeight = 400;
      const radius = Math.min(pieWidth, pieHeight) / 2;

      const svgPie = d3
        .select("#pie-chart")
        .append("svg")
        .attr("width", pieWidth)
        .attr("height", pieHeight);

      const color = d3
        .scaleOrdinal()
        .domain(pieData.map((d) => d.TYPE))
        .range(["#ff9999", "#66b3ff"]);

      const arc = d3
        .arc()
        .innerRadius(radius * 0.4)
        .outerRadius(radius)
        .padAngle(0.02);

      const pie = d3.pie().value((d) => d.count);

      const g = svgPie
        .append("g")
        .attr("transform", `translate(${pieWidth / 2}, ${pieHeight / 2})`);

      const pieSlices = g.selectAll("path").data(pie(pieData)).enter();

      pieSlices
        .append("path")
        .attr("d", arc)
        .attr("fill", (d) => color(d.data.TYPE))
        .attr("stroke", "white")
        .attr("stroke-width", "2px");

      //Percentaje lables
      const pieLabels = pieSlices
        .append("text")
        .attr("class", "pie-chart-label")
        .attr("text-anchor", "middle")
        .attr("transform", (d) => `translate(${arc.centroid(d)})`);
      //Calculation of %
      pieLabels.text((d) => {
        const total = d3.sum(pieData, (v) => v.count);
        const percent = Math.round((d.data.count / total) * 100);
        return `${percent}%`;
      });

      //Position of lable
      pieLabels.attr("dy", "2.1em");

      //Labels for each slice
      pieSlices
        .append("text")
        .attr("class", "pie-chart-label")
        .attr("text-anchor", "middle")
        .attr("transform", (d) => `translate(${arc.centroid(d)})`)
        .attr("dy", ".35em")
        .text((d) => d.data.TYPE);
    } else {
      console.log("No data for pie chart");
    }

    //GRAPH2 (CITIES SEACHED)--------------------------------------------------------------------------------------------------------------------------------------------------------------

    if (information.length > 0) {
      //Chart dimensions once created
      margin = { top: 20, right: 20, bottom: 50, left: 60 };
      width = 1000 - margin.left - margin.right;
      height = 400 - margin.top - margin.bottom;

      // Create the SVG element and append a group for easier manipulation
      const svg = d3
        .select("#bar-chart")
        .append("g")
        .attr("transform", `translate(${margin.left}, ${margin.top})`);

      // Set of data to respective axis Y & X
      const x = d3.scaleBand().range([0, width]).padding(0.1);
      const y = d3.scaleLinear().range([height, 0]);

      // Include respective labels and set the max amount of count into the Y axis
      x.domain(information.map((item) => item.CITY));
      y.domain([0, d3.max(information, (d) => d.count)]);

      //Apply color to each bar depending on values
      const colorScale = d3
        .scaleOrdinal()
        .domain(information.map((d) => d.count))
        .range(["#ff9999", "#66b3ff", "#99ff99", "#ffcc99"]);

      // Creation of bars
      svg
        .selectAll(".bar")
        .data(information)
        .enter()
        .append("rect")
        .attr("class", "bar")
        .attr("x", (d) => x(d.CITY))
        .attr("y", (d) => y(d.count))
        .attr("width", x.bandwidth())
        .attr("height", (d) => height - y(d.count))
        .attr("fill", (d) => colorScale(d.count));

      //Labels for x-axis
      svg
        .append("g")
        .attr("class", "x axis")
        .attr("transform", `translate(0, ${height})`)
        .call(d3.axisBottom(x));

      // Add X-Axis Label - adjust position and text as needed
      svg
        .append("text")
        .attr("class", "x-axis-label")
        .attr("text-anchor", "middle")
        .attr("x", width / 2)
        .attr("y", height + margin.bottom)
        .text("Cities");

      //Labels for y-axis
      svg.append("g").attr("class", "y axis").call(d3.axisLeft(y));

      // Add Y-Axis Label - adjust position and text
      svg
        .append("text")
        .attr("class", "y-axis-label")
        .attr("text-anchor", "middle")
        .attr("transform", "rotate(-90)")
        .attr("x", -height / 2)
        .attr("y", -margin.left + 20)
        .text("Count");
    } else {
      console.log("No data for bar chart");
    }
    //GRAPH3 -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    if (graph3.length > 0) {
      const pieData = graph3; // Assuming data.G3 holds user role information

      const pieWidth = 500;
      const pieHeight = 400;
      const radius = Math.min(pieWidth, pieHeight) / 2;

      const svgPie = d3
        .select("#PIECHART") // Use a new SVG element with id "PIECHART"
        .append("svg")
        .attr("width", pieWidth)
        .attr("height", pieHeight);

      const color = d3
        .scaleOrdinal()
        .domain(pieData.map((d) => d.Role)) // Assuming "Role" property exists
        .range(["#ff9999", "#66b3ff", "#99ff99"]); // Adjust color scheme as needed

      const arc = d3
        .arc()
        .innerRadius(radius * 0.4)
        .outerRadius(radius)
        .padAngle(0.02);

      const pie = d3.pie().value((d) => d.count); // Assuming "count" property exists

      const g = svgPie
        .append("g")
        .attr("transform", `translate(${pieWidth / 2}, ${pieHeight / 2})`);

      const pieSlices = g.selectAll("path").data(pie(pieData)).enter();

      pieSlices
        .append("path")
        .attr("d", arc)
        .attr("fill", (d) => color(d.data.Role)) // Assuming "Role" property exists
        .attr("stroke", "white")
        .attr("stroke-width", "2px");

      //Percentaje lables
      const pieLabels = pieSlices
        .append("text")
        .attr("class", "pie-chart-label")
        .attr("text-anchor", "middle")
        .attr("transform", (d) => `translate(${arc.centroid(d)})`);
      //Calculation of %
      pieLabels.text((d) => {
        const total = d3.sum(pieData, (v) => v.count);
        const percent = Math.round((d.data.count / total) * 100);
        return `${percent}%`;
      });

      //Position of lable
      pieLabels.attr("dy", "2.1em");

      //Labels for each slice
      pieSlices
        .append("text")
        .attr("class", "pie-chart-label")
        .attr("text-anchor", "middle")
        .attr("transform", (d) => `translate(${arc.centroid(d)})`)
        .attr("dy", ".35em")
        .text((d) => d.data.Role); // Assuming "Role" property exists
    } else {
      console.log("No data for Graph3 pie chart");
    }
  });

  let isDropdownOpen = false;
  const handleDropdownClick = () => {
    isDropdownOpen = !isDropdownOpen;
  };

  const handleDropdownFocusLoss = ({ relatedTarget, currentTarget }) => {
    if (
      relatedTarget instanceof HTMLElement &&
      currentTarget.contains(relatedTarget)
    )
      return (isDropdownOpen = false);
  };
</script>

<div class="flex items-center mt-4 mb-8 gap-x-3">
  <h1 class="text-xl font-bold">Analytics</h1>
</div>
<div class="flex justify-between items-center">
  <div class="dropdown" on:focusout={handleDropdownFocusLoss}>
    <button class="btn m-1" on:click={handleDropdownClick}>
      {#if isDropdownOpen}
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          class="inline-block h-6 w-6 stroke-current"
        >
          <title>Close Dropdown</title>
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M6 18L18 6M6 6l12 12"
          />
        </svg>
      {:else}
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          class="inline-block h-6 w-6 stroke-current"
        >
          <title>Open Dropdown</title>
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M4 6h16M4 12h16M4 18h16"
          />
        </svg>
      {/if}
    </button>
    <ul
      class="dropdown-content menu p-2 shadow bg-base-100 rounded-box w-52"
      style:visibility={isDropdownOpen ? "visible" : "hidden"}
    >
      <li><button class="btn text-slate-800" on:click={() => selection = "List"}>List</button></li>
      <li><button class="btn text-slate-800"on:click={() => selection = "Graphics"}>Graphics</button></li>
    </ul>
  </div>
</div>
<div class="flex flex-col items-center">
  <h2 class="text-3xl font-bold text-center">Number of Searches by City</h2>
  <svg id="bar-chart" height="400" width="1000"></svg>
  <div class="flex">
    <div>
      <h2 class="text-3xl font-bold text-center mt-20 mb-5">
        Roundtrip vs. Oneway Flight Searches
      </h2>
      <svg id="pie-chart" height="400" width="500"></svg>
    </div>
    <div>
      <h2 class="text-3xl font-bold text-center mt-20 mb-5">
        Purchases by User Type
      </h2>
      <svg id="PIECHART" height="400" width="500"></svg>
    </div>
  </div>
</div>
