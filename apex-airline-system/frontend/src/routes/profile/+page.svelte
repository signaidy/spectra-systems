<script>
	import Header from '../../components/header.svelte';
    import Footer from '../../components/footer.svelte'
</script>

<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet" />

<div class="bgpf">
	<div class="w-full text-white">
		<div>
			<Header />
		</div>
	</div>

	<div class="w-full container mx-auto my-5 p-5">
		<div class="md:flex no-wrap md:-mx-2">
			<!-- Left Side -->
			<div class="w-full md:w-3/12 md:mx-2">
				<!-- Profile Card -->
				<div class="bg-white p-3 border-t-4 border-blue-400">
					<div class="image overflow-hidden">
						<img
							class="h-auto w-full mx-auto"
							src="https://lavinephotography.com.au/wp-content/uploads/2017/01/PROFILE-Photography-112.jpg"
							alt=""
						/>
					</div>
					<h1 class="text-gray-900 font-bold text-xl leading-8 my-1">Jane Doe</h1>
					<ul
						class="bg-gray-100 text-gray-600 hover:text-gray-700 hover:shadow py-2 px-3 mt-3 divide-y rounded shadow-sm"
					>
						<li class="flex items-center py-3">
							<span>Status</span>
							<span class="ml-auto"
								><span class="bg-green-500 py-1 px-2 rounded text-white text-sm">Active</span></span
							>
						</li>
						<li class="flex items-center py-3">
							<span>Member since</span>
							<span class="ml-auto">Nov 07, 2016</span>
						</li>
						<li >
							<a class="flex items-center py-3" href="/administration">
							<span>Adminsitrator Page</span>
							<svg class="iconadmin" xmlns="http://www.w3.org/2000/svg" width="1.4em" height="1.5em" viewBox="0 0 64 64" {...$$props}>
								<path fill="currentColor" d="M56.277 20.663a7.294 7.294 0 1 1-14.588 0a7.294 7.294 0 0 1 14.588 0M3.292 6.261V19.49H1.314a.677.677 0 0 1-.679-.678V1.742c0-.578.431-1.048 1.022-1.048h8.747c.59 0 1.022.469 1.022 1.046v1.002h13.523c.378 0 .68.303.68.676v3.838H14.085V6.251c0-.577-.432-1.046-1.024-1.046H4.317c-.592 0-1.024.477-1.024 1.056z" />
								<path fill="currentColor" d="M30.946 27.5a.676.676 0 0 1-.68.676H6.631a.676.676 0 0 1-.679-.677v-17.07c0-.578.431-1.048 1.022-1.048h8.745c.592 0 1.024.469 1.024 1.046v1.002h13.523c.377 0 .68.303.68.676zm-10.484-9.719v-4.026h-4.028v4.026h-4.027v4.028h4.027v4.027h4.028v-4.027h4.027v-4.028zM56.19 29.836a7.311 7.311 0 0 1 7.317 7.31l.015 26.733H38.978V41.718h-.994l-4.526 6.753c-.737 1.005-2.233 1.492-3.426 1.492H19.406a3.197 3.197 0 0 1-3.198-3.193c0-1.76 1.433-3.15 3.198-3.15h9.665l6.705-10.138c1.606-2.452 4.748-3.637 7.277-3.637l13.136-.008z" />
							</svg>
							</a>
						</li>
					</ul>
				</div>
				<!-- End of profile card -->
				<div class="my-4"></div>
			</div>
			<!-- Right Side -->
			<div class="w-full md:w-9/12 mx-2 h-64">
				<!-- Profile tab -->
				<!-- About Section -->
				<div class="bg-white p-3 shadow-sm rounded-sm">
					<div class="flex items-center space-x-2 font-semibold text-gray-900 leading-8">
						<span>
							<svg
								class="h-5"
								xmlns="http://www.w3.org/2000/svg"
								fill="none"
								viewBox="0 0 24 24"
								stroke="currentColor"
							>
								<path
									stroke-linecap="round"
									stroke-linejoin="round"
									stroke-width="2"
									d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"
								/>
							</svg>
						</span>
						<span class="tracking-wide">About</span>
					</div>
					<div class="text-gray-700">
						<div class="grid md:grid-cols-2 text-sm">
							<div class="grid grid-cols-2">
								<div class="px-4 py-2 font-semibold">First Name</div>
								<div class="px-4 py-2">Jane</div>
							</div>
							<div class="grid grid-cols-2">
								<div class="px-4 py-2 font-semibold">Passport number</div>
								<div class="px-4 py-2">xxxxxxx</div>
							</div>
                            <div class="grid grid-cols-2">
								<div class="px-4 py-2 font-semibold">Last Name</div>
								<div class="px-4 py-2">Doe</div>
							</div>
	
							<div class="grid grid-cols-2">
								<div class="px-4 py-2 font-semibold">Age</div>
								<div class="px-4 py-2">xxxxxxxxx</div>
							</div>
                            <div class="grid grid-cols-2">
								<div class="px-4 py-2 font-semibold">Email.</div>
								<div class="px-4 py-2">
									<a class="text-blue-800" href="mailto:jane@example.com">jane@example.com</a>
								</div>
							</div>
							<div class="grid grid-cols-2">
								<div class="px-4 py-2 font-semibold">Origin country</div>
								<div class="px-4 py-2">Arlington Heights, IL, Illinois</div>
							</div>
					
						</div>
					</div>
				</div>
				<!-- End of about section -->
				<!-- End of profile tab -->
			</div>
		</div>
	</div>
</div>

<!-- component -->
<style>
	:root {
		--main-color: #4a76a8;
	}

    .bgpf {
        height: 995px;
        background-color:#f1f2f3;
    }

	.iconadmin { 
		margin-left: 89px;
	}

</style>
