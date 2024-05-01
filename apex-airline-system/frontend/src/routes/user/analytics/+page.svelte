<script lang="ts">
  import * as d3 from "d3";
  import { onMount } from "svelte";
  export let data;

  let margin, width, height;

  onMount(async () => {
    const information = await data.G2;

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
  });
</script>

<div class="flex items-center mt-4 mb-8 gap-x-3">
  <h1 class="text-xl font-bold">Analytics</h1>
</div>
<div class="mx-10">
  <svg id="bar-chart" height="10000" width="10000"></svg>
</div>
