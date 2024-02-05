<script>
	import AirlineImage from '../assets/airline.svg';
	import TabImage from '../assets/TabImage.svg'
</script>


<link class="tabimage" rel="icon" type="image/svg" href={TabImage}/> 

<header class="bg-blue-400">
	<nav class="p-6">
		<div class="flex justify-between items-center">
			<a href="/home">
				<img class="pr-6 border-r-2" src={AirlineImage} title="logo" alt="logo" />
			</a>
			<div class="flex justify-between flex-grow">
				<div class="flex ml-6 items-center">
					<form class="flex items-center">
						<label for="voice-search" class="sr-only">Search</label>
						<div class="relative w-full">
							<div class="flex absolute inset-y-0 left-0 items-center pl-3 pointer-events-none">
								<svg
									aria-hidden="true"
									class="w-5 h-5 text-gray-500 dark:text-gray-400"
									fill="currentColor"
									viewBox="0 0 20 20"
									xmlns="http://www.w3.org/2000/svg"
									><path
										fill-rule="evenodd"
										d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
										clip-rule="evenodd"
									></path></svg
								>
							</div>
							<input
								type="text"
								id="voice-search"
								class="bar bg-gray-50 border border-gray-300 text-gray-900 text-sm focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
								placeholder="Find the best flights at your disposal"
								required
							/>
						</div>
						<button
							type="submit"
							class="inline-flex items-center py-2.5 px-3 ml-2 text-sm font-medium text-white bg-blue-700 border border-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
						>
							Search
						</button>
					</form>
				</div>

				<div class="md:flex space-x-6 hidden">
					<svg
						xmlns="http://www.w3.org/2000/svg"
						width="2.5em"
						height="2.5em"
						viewBox="0 0 24 24"
						{...$$props}
					>
						<path
							fill="currentColor"
							d="M17 18c-1.11 0-2 .89-2 2a2 2 0 0 0 2 2a2 2 0 0 0 2-2a2 2 0 0 0-2-2M1 2v2h2l3.6 7.59l-1.36 2.45c-.15.28-.24.61-.24.96a2 2 0 0 0 2 2h12v-2H7.42a.25.25 0 0 1-.25-.25c0-.05.01-.09.03-.12L8.1 13h7.45c.75 0 1.41-.42 1.75-1.03l3.58-6.47c.07-.16.12-.33.12-.5a1 1 0 0 0-1-1H5.21l-.94-2M7 18c-1.11 0-2 .89-2 2a2 2 0 0 0 2 2a2 2 0 0 0 2-2a2 2 0 0 0-2-2"
						/>
					</svg>
					<a href="/profile">
					<svg
						xmlns="http://www.w3.org/2000/svg"
						width="2.5em"
						height="2.5em"
						viewBox="0 0 24 24"
						{...$$props}
					>
						<path
							fill="currentColor"
							fill-rule="evenodd"
							d="M12 4a8 8 0 0 0-6.96 11.947A4.99 4.99 0 0 1 9 14h6a4.99 4.99 0 0 1 3.96 1.947A8 8 0 0 0 12 4m7.943 14.076A9.959 9.959 0 0 0 22 12c0-5.523-4.477-10-10-10S2 6.477 2 12a9.958 9.958 0 0 0 2.057 6.076l-.005.018l.355.413A9.98 9.98 0 0 0 12 22a9.947 9.947 0 0 0 5.675-1.765a10.055 10.055 0 0 0 1.918-1.728l.355-.413zM12 6a3 3 0 1 0 0 6a3 3 0 0 0 0-6"
							clip-rule="evenodd"
						/>
					</svg>
				</a>
				</div>
			</div>
		</div>
	</nav>
</header>

<style>
	.bar {
		width: 400px;
	}

</style>
