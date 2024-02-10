<script>
    import {fetch} from 'node-fetch';

    let users = []; // List of all users
    let selectedUserId = undefined; // Selected user ID for update

    async function fetchUsers() {
        const response = await fetch('http://localhost:8080/nexus/users');
        if (!response.ok) {
            throw new Error('Failed to fetch users');
        }
        users = await response.json();
    }

    async function updateUser(userId) {
        const user = users.find((u) => u.id === userId);
        if (!user) {
            throw new Error(`User with ID ${userId} not found`);
        }

        const updatedUser = {
            // Update user data, potentially using values from dynamic list form inputs
            ...user, // Preserve existing user data
            // Example field update:
            firstName: document
                .getElementById(`firstName-${userId}`)
                .value
        };

        try {
            const response = await fetch(`http://localhost:8080/nexus/users/${userId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedUser)
            });

            if (!response.ok) {
                throw new Error('Failed to update user');
            }

            const updated = await response.json();
            console.log('User updated:', updated);
            // Update user data in the list
            const index = users.findIndex((u) => u.id === userId);
            users[index] = updated;
        } catch (error) {
            console.error('Error updating user:', error);
            // Handle error gracefully
        }
    }

    fetchUsers().catch(console.error);

    $ : selectedUserId = undefined;

    function onSelectUser(userId) {
        selectedUserId = userId;
    }
</script>

{#each users as user}
    <form on:submit={() => updateUser(user.id)}>
        <div>
            <label for={`userId-${user.id} `}>User ID:</label>
            <span>{user.id}</span>
        </div>
        <div>
            <label for={`firstName-${user.id} `}>First Name:</label>
            <input type="text" id={`firstName-${user.id} `} bind:value={user.firstName}/>
        </div>
        <div></div>
        <button type="submit" disabled={selectedUserId !== user.id}>Update</button>
    </form>
{/each}

{#each users as user}
    <button on:click={() => onSelectUser(user.id)}>Select
        {user.id}</button>
{/each}