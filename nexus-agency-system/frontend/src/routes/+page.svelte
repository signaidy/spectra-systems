<script>
    import { Link } from '@sveltejs/kit';
    import UserList from './UserList.svelte';
    import UserForm from './CreateUser.svelte';
</script>

<style>
    .container {
        display: flex;
        justify-content: space-between;
    }
    .left {
        flex: 1;
        padding-right: 20px;
    }
    .right {
        flex: 1;
        padding-left: 20px;
    }
</style>

<nav>
    <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/users">User List</Link></li>
        <li><Link to="/users/new">Create User</Link></li>
    </ul>
</nav>

<div class="container">
    <div class="left">
        <UserForm />
    </div>
    <div class="right">
        <UserList />
    </div>
</div>

<slot></slot>