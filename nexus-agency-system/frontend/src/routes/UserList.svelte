<script context="module">
    export async function load() {
        const res = await fetch('http://localhost:8080/nexus/users');
        const users = await res.json();
        return {
            props: { users }
        };
    }
</script>

<script>
    export let users;
</script>

<h1>User List</h1>

<ul>
    {#each users as user (user.id)}
        <li>
            <p><strong>Name:</strong> {user.firstName} {user.lastName}</p>
            <p><strong>Email:</strong> {user.email}</p>
            <p><strong>Age:</strong> {user.age}</p>
            <p><strong>Country:</strong> {user.country}</p>
            <p><strong>Passport:</strong> {user.passport}</p>
            <hr>
        </li>
    {/each}
</ul>
