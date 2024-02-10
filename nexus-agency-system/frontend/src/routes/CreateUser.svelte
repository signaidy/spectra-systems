<script>
    import { fetch } from 'node-fetch';
  
    let firstName = '';
    let lastName = '';
    let email = '';
    let age = '';
    let password = '';
    let country = '';
    let passport = '';
  
    async function createUser() {
      const user = {
        firstName,
        lastName,
        email,
        age,
        password,
        country,
        passport,
      };
  
      try {
        const response = await fetch('http://localhost:8080/nexus/users', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(user),
        });
  
        if (!response.ok) {
          throw new Error('Failed to create user');
        }
  
        const createdUser = await response.json();
        console.log('User created:', createdUser);
        // Clear form fields or display success message
      } catch (error) {
        console.error('Error creating user:', error);
        // Handle error gracefully, e.g., display an error message
      }
    }
  </script>
  
  <form on:submit={() => createUser()}>
    <div>
      <label for="firstName">First Name:</label>
      <input type="text" id="firstName" bind:value={firstName} />
    </div>
    <div>
      <label for="lastName">Last Name:</label>
      <input type="text" id="lastName" bind:value={lastName} />
    </div>
    <div>
      <label for="email">Email:</label>
      <input type="email" id="email" bind:value={email} />
    </div>
    <div>
      <label for="age">Age:</label>
      <input type="number" id="age" bind:value={age} />
    </div>
    <div>
      <label for="password">Password:</label>
      <input type="password" id="password" bind:value={password} />
    </div>
    <div>
      <label for="country">Country:</label>
      <input type="text" id="country" bind:value={country} />
    </div>
    <div>
      <label for="passport">Passport:</label>
      <input type="text" id="passport" bind:value={passport} />
    </div>
    <button type="submit">Create User</button>
  </form>
  