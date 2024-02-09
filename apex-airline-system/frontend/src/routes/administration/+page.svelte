<script>
   // Components 
	import Header from '../../components/header.svelte';
	import UserEnterpriseconf from '../../assets/enterprise-config.png';
	import Dashboard from '../../components/dashboard.svelte';
   import Users from '../../components/user_admin.svelte'; 
   // Assets
   import WelcomeImage from '../../assets/trophy-icon.svg'
	import UserAdmin from '../../components/user_admin.svelte';

   let welcome = true;
	let dashboardinfo = false;
   let usersinformation = false


	function dashboardClick() {
      welcome = false;
      usersinformation = false; 
		dashboardinfo = true;
		console.log('HOLA');
	}

   function usersClick() {
      welcome = false;
      usersinformation = true; 
		dashboardinfo = false;
		console.log('HOLA');
	}

</script>

<div>
	<Header />
	<div class="flex overflow-hidden bg-white">
		<aside
			id="sidebar"
			class="relative z-20 h-full top-0 left-0 flex lg:flex flex-shrink-0 flex-col w-50"
			aria-label="Sidebar"
		>
			<div class="relative flex-1 flex flex-col min-h-0 border-gray-200 bg-white pt-0">
				<div class="flex-1 flex flex-col pt-5 pb-4 overflow-y-auto">
					<div class="flex-1 px-3 bg-white divide-y space-y-1">
						<ul class="space-y-2 pb-2">
							<li>
								<form action="#" method="GET" class="lg:hidden">
									<!-- <label for="mobile-search" class="sr-only">Search</label> -->
									<div class="relative">
										<div
											class="relative inset-y-0 left-0 pl-3 flex items-center pointer-events-none"
										>
											<svg
												class="w-5 h-5 text-gray-500"
												fill="currentColor"
												viewBox="0 0 20 20"
												xmlns="http://www.w3.org/2000/svg"
											>
												<path
													d="M5 3a2 2 0 00-2 2v2a2 2 0 002 2h2a2 2 0 002-2V5a2 2 0 00-2-2H5zM5 11a2 2 0 00-2 2v2a2 2 0 002 2h2a2 2 0 002-2v-2a2 2 0 00-2-2H5zM11 5a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2V5zM11 13a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2v-2z"
												></path>
											</svg>
										</div>
										<!-- <input type="text" name="email" id="mobile-search" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-cyan-600 focus:ring-cyan-600 block w-full pl-10 p-2.5" placeholder="Search"> -->
									</div>
								</form>
							</li>
							<li>
								<a
									on:click={dashboardClick}
									href="#"
									class="text-base text-gray-900 font-normal rounded-lg flex items-center p-2 hover:bg-gray-100 group"
								>
									<svg
										class="w-6 h-6 text-gray-500 group-hover:text-gray-900 transition duration-75"
										fill="currentColor"
										viewBox="0 0 20 20"
										xmlns="http://www.w3.org/2000/svg"
									>
										<path d="M2 10a8 8 0 018-8v8h8a8 8 0 11-16 0z"></path>
										<path d="M12 2.252A8.014 8.014 0 0117.748 8H12V2.252z"></path>
									</svg>
									<span class="ml-3">Dashboard</span>
								</a>
							</li>
							<li>
								<a
                           on:click={usersClick}
									href="#"
									class="text-base text-gray-900 font-normal rounded-lg hover:bg-gray-100 flex items-center p-2 group"
								>
									<svg
										class="w-6 h-6 text-gray-500 flex-shrink-0 group-hover:text-gray-900 transition duration-75"
										fill="currentColor"
										xmlns="http://www.w3.org/2000/svg"
										viewBox="0 0 640 512"
									>
										<path
											fill="currentColor"
											d="M224 0a128 128 0 1 1 0 256a128 128 0 1 1 0-256m-45.7 304h91.4c11.8 0 23.4 1.2 34.5 3.3c-2.1 18.5 7.4 35.6 21.8 44.8c-16.6 10.6-26.7 31.6-20 53.3c4 12.9 9.4 25.5 16.4 37.6s15.2 23.1 24.4 33c15.7 16.9 39.6 18.4 57.2 8.7v.9c0 9.2 2.7 18.5 7.9 26.3H29.7C13.3 512 0 498.7 0 482.3C0 383.8 79.8 304 178.3 304M436 218.2c0-7 4.5-13.3 11.3-14.8c10.5-2.4 21.5-3.7 32.7-3.7s22.2 1.3 32.7 3.7c6.8 1.5 11.3 7.8 11.3 14.8v17.7c0 7.8 4.8 14.8 11.6 18.7c6.8 3.9 15.1 4.5 21.8.6l13.8-7.9c6.1-3.5 13.7-2.7 18.5 2.4c7.6 8.1 14.3 17.2 20.1 27.2s10.3 20.4 13.5 31c2.1 6.7-1.1 13.7-7.2 17.2l-14.4 8.3c-6.5 3.7-10 10.9-10 18.4s3.5 14.7 10 18.4l14.4 8.3c6.1 3.5 9.2 10.5 7.2 17.2c-3.3 10.6-7.8 21-13.5 31s-12.5 19.1-20.1 27.2c-4.8 5.1-12.5 5.9-18.5 2.4l-13.8-7.9c-6.7-3.9-15.1-3.3-21.8.6c-6.8 3.9-11.6 10.9-11.6 18.7v17.7c0 7-4.5 13.3-11.3 14.8c-10.5 2.4-21.5 3.7-32.7 3.7s-22.2-1.3-32.7-3.7c-6.8-1.5-11.3-7.8-11.3-14.8v-17.6c0-7.9-4.9-14.9-11.7-18.9c-6.8-3.9-15.2-4.5-22-.6l-13.5 7.8c-6.1 3.5-13.7 2.7-18.5-2.4c-7.6-8.1-14.3-17.2-20.1-27.2s-10.3-20.4-13.5-31c-2.1-6.7 1.1-13.7 7.2-17.2l14-8.1c6.5-3.8 10.1-11.1 10.1-18.6s-3.5-14.8-10.1-18.6l-14-8.1c-6.1-3.5-9.2-10.5-7.2-17.2c3.3-10.6 7.7-21 13.5-31s12.5-19.1 20.1-27.2c4.8-5.1 12.4-5.9 18.5-2.4l13.6 7.8c6.8 3.9 15.2 3.3 22-.6c6.9-3.9 11.7-11 11.7-18.9v-17.2zm92.1 133.5a48.1 48.1 0 1 0-96.1 0a48.1 48.1 0 1 0 96.1 0"
										/>
									</svg>
									<span class="ml-3 flex-1 whitespace-nowrap">Users</span>
								</a>
							</li>
							<li>
								<a
									href="#"
									class="text-base text-gray-900 font-normal rounded-lg hover:bg-gray-100 flex items-center p-2 group"
								>
									<img
										class="w-6 h-6 text-gray-500 flex-shrink-0 group-hover:text-gray-900 transition duration-75"
										src={UserEnterpriseconf}
										alt="configenter"
									/>
									<span class="ml-3 flex-1 whitespace-nowrap">Enterprise users</span>
								</a>
							</li>
							<li>
								<a
									href="#"
									class="text-base text-gray-900 font-normal rounded-lg hover:bg-gray-100 flex items-center p-1 group"
								>
									<svg
										class="w-6 h-6 text-gray-500 flex-shrink-0 group-hover:text-gray-900 transition duration-75"
										fill="currentColor"
										viewBox="0 0 20 20"
										xmlns="http://www.w3.org/2000/svg"
									>
										<path
											fill="currentColor"
											d="m6.85 17.15l-3.2-1.75l1.05-1.05l2.5.35l3.9-3.9l-7.8-4.25l1.4-1.4l9.55 2.45l3.925-3.875Q18.6 3.3 19.238 3.3t1.062.425q.425.425.425 1.063T20.3 5.85l-3.9 3.9l2.45 9.55l-1.4 1.4l-4.25-7.8l-3.9 3.9l.35 2.5l-1.05 1.05z"
										/>
									</svg>
									<span class="ml-3 flex-1 whitespace-nowrap">Flights</span>
								</a>
							</li>
							<li>
								<a
									href="#"
									class="text-base text-gray-900 font-normal rounded-lg hover:bg-gray-100 flex items-center p-2 group"
								>
									<svg
										class="w-6 h-6 text-gray-500 flex-shrink-0 group-hover:text-gray-900 transition duration-75"
										fill="currentColor"
										xmlns="http://www.w3.org/2000/svg"
										viewBox="0 0 2048 2048"
									>
										<path
											fill="currentColor"
											d="M2048 640v1152H0V640h440q1 0 33-10t87-29t126-42t154-51t169-57t172-58t161-54t139-46t103-35t56-19l134 401zm-1206 0h797l-79-239zm1078 128H128v896h1792zm-256 128v128h-128V896zm0 256v128h-128v-128zm0 384h-128v-128h128zm128-384h-128v-128h128zm0 256h-128v-128h128zM469 1280l-85-256h128l64 128h277l-85-256h128l128 256h256q26 0 45 19t19 45q0 26-19 45t-45 19h-256l-128 256H768l85-256z"
										/>
									</svg>
									<span class="ml-3 flex-1 whitespace-nowrap">Tcikets - administration</span>
								</a>
							</li>
						</ul>
						<div class="space-y-2 pt-2">
							<a
								href="#"
								target="_blank"
								class="text-base text-gray-900 font-normal rounded-lg hover:bg-gray-100 group transition duration-75 flex items-center p-2"
							>
								<svg
									class="w-6 h-6 text-gray-500 flex-shrink-0 group-hover:text-gray-900 transition duration-75"
									fill="currentColor"
									viewBox="0 0 20 20"
									xmlns="http://www.w3.org/2000/svg"
								>
									<path d="M9 2a1 1 0 000 2h2a1 1 0 100-2H9z"></path>
									<path
										fill-rule="evenodd"
										d="M4 5a2 2 0 012-2 3 3 0 003 3h2a3 3 0 003-3 2 2 0 012 2v11a2 2 0 01-2 2H6a2 2 0 01-2-2V5zm3 4a1 1 0 000 2h.01a1 1 0 100-2H7zm3 0a1 1 0 000 2h3a1 1 0 100-2h-3zm-3 4a1 1 0 100 2h.01a1 1 0 100-2H7zm3 0a1 1 0 100 2h3a1 1 0 100-2h-3z"
										clip-rule="evenodd"
									></path>
								</svg>
								<span class="ml-3">Documentation</span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</aside>
		{#if welcome == true}
			<div
				class="relative welcomestyle bg-blue-400 shadow-lg w-60 h-50 border-2 border-gray-500 flex items-center flex-col "
			>
				<div class="absolute -top-10">
					<img
						class=" h-20 w-20 object-cover"
						src={WelcomeImage}
						alt=""
					/>
				</div>
				<div class="mt-16 flex items-center flex-col justify-center">
					<h1 class="font-bold text-2xl mt-4">Welcome!</h1>
					<p class="font-semibold text-xl text-gray-500">Adminsitrator</p>
					<p class="text-center mt-4">
						Name of user
					</p>
				</div>
				<div class=" flex flex-row space-x-4 mt-11">
				</div>
			</div>
		{:else if dashboardinfo == true}
			<Dashboard />
      {:else if usersinformation == true}
			<UserAdmin />
		{/if}
		<!-- <Dashboard/> -->
	</div>
	<script async defer src="https://buttons.github.io/buttons.js"></script>
	<script src="https://demo.themesberg.com/windster/app.bundle.js"></script>
</div>

<style>
	.welcomestyle {
		margin-left: 650px;
		margin-top: 100px;
	}
</style>
