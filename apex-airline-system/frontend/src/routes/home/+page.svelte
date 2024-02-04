<style>
.bar {
    width: 400px;
}
</style>


<body class="bg-blue-400">
	<header>
		<nav class="p-6">
			<div class="flex justify-between items-center">
				<img
					class="pr-6 border-r-2"
					src="https://www.allegiantair.jobs/wp-content/uploads/2021/10/logo.svg"
					title="logo"
					alt="logo"
				/>
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
									placeholder="Search Mockups, Logos, Design Templates..."
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
					<!-- <div class="flex ml-6 items-center">
              <span>
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-4 cursor-pointer text-gray-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
                </svg>
              </span><div class="flex ml-6 items-center">
              <span>
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-4 cursor-pointer text-gray-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
                </svg>
              </span>
              <input class="outline-none text-sm flex-grow bg-gray-100" type="text" placeholder="Search saunas in Finland…" />
            </div>
              <input class="outline-none text-sm flex-grow bg-gray-100" type="text" placeholder="Search saunas in Finland…" />
            </div> -->

					<div class="md:flex space-x-6 hidden">
						<svg xmlns="http://www.w3.org/2000/svg" width="2.5em" height="2.5em" viewBox="0 0 24 24" {...$$props}>
                            <path fill="currentColor" d="M17 18c-1.11 0-2 .89-2 2a2 2 0 0 0 2 2a2 2 0 0 0 2-2a2 2 0 0 0-2-2M1 2v2h2l3.6 7.59l-1.36 2.45c-.15.28-.24.61-.24.96a2 2 0 0 0 2 2h12v-2H7.42a.25.25 0 0 1-.25-.25c0-.05.01-.09.03-.12L8.1 13h7.45c.75 0 1.41-.42 1.75-1.03l3.58-6.47c.07-.16.12-.33.12-.5a1 1 0 0 0-1-1H5.21l-.94-2M7 18c-1.11 0-2 .89-2 2a2 2 0 0 0 2 2a2 2 0 0 0 2-2a2 2 0 0 0-2-2" />
                        </svg>
                        <svg xmlns="http://www.w3.org/2000/svg" width="2.5em" height="2.5em" viewBox="0 0 24 24" {...$$props}>
                            <path fill="currentColor" fill-rule="evenodd" d="M12 4a8 8 0 0 0-6.96 11.947A4.99 4.99 0 0 1 9 14h6a4.99 4.99 0 0 1 3.96 1.947A8 8 0 0 0 12 4m7.943 14.076A9.959 9.959 0 0 0 22 12c0-5.523-4.477-10-10-10S2 6.477 2 12a9.958 9.958 0 0 0 2.057 6.076l-.005.018l.355.413A9.98 9.98 0 0 0 12 22a9.947 9.947 0 0 0 5.675-1.765a10.055 10.055 0 0 0 1.918-1.728l.355-.413zM12 6a3 3 0 1 0 0 6a3 3 0 0 0 0-6" clip-rule="evenodd" />
                        </svg>					</div>
				</div>
			</div>
		</nav>
		<!-- Section Hero -->
		<div class="container mx-auto bg-gray-400 h-96 rounded-md flex items-center">
			<div class="sm:ml-20 text-gray-50 text-center sm:text-left">
				<h1 class="text-5xl font-bold mb-4">
					Book saunas <br />
					everywhere.
				</h1>
				<p class="text-lg inline-block sm:block">
					The largest online community to rent saunas in Finland.
				</p>
				<button class="mt-8 px-4 py-2 bg-gray-600 rounded">Browse saunas</button>
			</div>
		</div>
	</header>
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
	<footer class="mb-6 px-6 md:px-0">
		<div class="grid gap-5 grid-cols-5 container mx-auto space-x-6">
			<div>
				<img
					class="pr-6"
					src="https://www.allegiantair.jobs/wp-content/uploads/2021/10/logo.svg"
					title="logo"
					alt="logo"
				/>
				<p>The largest online community to rent saunas in Finland.</p>
				<spa>© Sharetribe 2017.</spa>
			</div>
			<div class="pt-2">
				<ul>
					<li>Add your sauna</li>
					<li>About us</li>
					<li>F.A.Q</li>
					<li>Help</li>
					<li>Contact</li>
				</ul>
			</div>
			<div class="pt-2">
				<ul>
					<li>Helsinki</li>
					<li>Espoo</li>
					<li>Ruka</li>
					<li>Tampere</li>
					<li>Turku</li>
				</ul>
			</div>
			<div class="pt-2">
				<ul>
					<li>Kuopio</li>
					<li>Mikkeli</li>
					<li>Espoo</li>
					<li>Vantaa</li>
					<li>Ahvenanmaa</li>
				</ul>
			</div>
			<div class="pt-2">
				<div>
					<div class="flex space-x-4 text-gray-500 text-center">
						<span
							><svg
								xmlns="http://www.w3.org/2000/svg"
								class="h-6 w-6"
								fill="none"
								viewBox="0 0 24 24"
								stroke="currentColor"
							>
								<path
									stroke-linecap="round"
									stroke-linejoin="round"
									stroke-width="2"
									d="M8.228 9c.549-1.165 2.03-2 3.772-2 2.21 0 4 1.343 4 3 0 1.4-1.278 2.575-3.006 2.907-.542.104-.994.54-.994 1.093m0 3h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
								/></svg
							></span
						>
						<span
							><svg
								xmlns="http://www.w3.org/2000/svg"
								class="h-6 w-6"
								fill="none"
								viewBox="0 0 24 24"
								stroke="currentColor"
							>
								<path
									stroke-linecap="round"
									stroke-linejoin="round"
									stroke-width="2"
									d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z"
								/></svg
							></span
						>
						<span
							><svg
								xmlns="http://www.w3.org/2000/svg"
								class="h-6 w-6"
								fill="none"
								viewBox="0 0 24 24"
								stroke="currentColor"
							>
								<path
									stroke-linecap="round"
									stroke-linejoin="round"
									stroke-width="2"
									d="M16 8l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2M5 3a2 2 0 00-2 2v1c0 8.284 6.716 15 15 15h1a2 2 0 002-2v-3.28a1 1 0 00-.684-.948l-4.493-1.498a1 1 0 00-1.21.502l-1.13 2.257a11.042 11.042 0 01-5.516-5.517l2.257-1.128a1 1 0 00.502-1.21L9.228 3.683A1 1 0 008.279 3H5z"
								/></svg
							></span
						>
					</div>
					<div class="flex mt-12">
						<p>Privacy & Cookies</p>
						<p>Terms of use</p>
					</div>
				</div>
			</div>
		</div>
	</footer>
</body>
