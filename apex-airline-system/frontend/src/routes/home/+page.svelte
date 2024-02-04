<script>
	import Header from '../../components/header.svelte';
	import Footer from '../../components/footer.svelte';
</script>

<body class="bg-blue-400">
	<Header />

	<main class="py-16 container mx-auto px-6 md:px-0">
		<section>
			<h1 class="text-3xl font-bold text-gray-600 mb-10">Explore exotic locations in Finland</h1>
			<div class="grid sm:grid-cols-3 gap-4 grid-cols-2">
				<div>
					<div class="bg-gray-300 h-44"></div>
					<h3 class="text-lg font-semibold text-gray-500 mt-2">
						Saunas in <span class="text-gray-700">Helsinki</span>
					</h3>
				</div>
				<div>
					<div class="bg-gray-300 h-44"></div>
					<h3 class="text-lg font-semibold text-gray-500 mt-2">
						Saunas in <span class="text-gray-700">Rovaniemi</span>
					</h3>
				</div>
				<div>
					<div class="bg-gray-300 h-44"></div>
					<h3 class="text-lg font-semibold text-gray-500 mt-2">
						Saunas in <span class="text-gray-700">Ruka</span>
					</h3>
				</div>
			</div>
			<hr class="w-40 my-14 border-4 rounded-md sm:mx-0 mx-auto" />
		</section>
		<section>
			<h1 class="inline-block text-gray-600 font-bold text-3xl">
				The holy sauna ritual <br />
				(or how does Saunatime work).
			</h1>

			<div class="grid grid-cols-3 gap-4 mt-10">
				<div>
					<h3 class="text-lg font-semibold text-gray-500 mt-2">1. Browse and book</h3>
					<p class="text text-gray-400">
						Start by searching for a location. Once you find a sauna you like, simply check the
						availability, book it, and make a secure payment right away.
					</p>
				</div>
				<div>
					<h3 class="text-lg font-semibold text-gray-500 mt-2">2. Have a great bath</h3>
					<p class="text text-gray-400">
						Meet your host on the date you chose and enjoy the home sauna experience. We'll handle
						the payment to the host after your experience.
					</p>
				</div>
				<div>
					<h3 class="text-lg font-semibold text-gray-500 mt-2">3. Review the host</h3>
					<p class="text text-gray-400">
						If you enjoyed the experience, let others know by giving a review to your sauna host.
						This way others will know where to go.
					</p>
				</div>
			</div>
		</section>
		<div class="mt-14">
			<p>Ps. You can also become a Saunatime host in few clicks!</p>
		</div>
	</main>
	<Footer />
</body>

<style>
	.bar {
		width: 400px;
	}
</style>
