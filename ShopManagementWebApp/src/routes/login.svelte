<script lang="ts">
    let login: string = ""
    let password: string = ""

    const onLogin = () => {
        console.log(login, password)
    }
</script>

<div class="w-full h-full mt-32 center">

    <div class="v-stack md:min-w-[500px] p-5 bg-bg-200 rounded-md shadow-dark-md">
        <div class="text-3xl w-full center py-4">Login</div>
        <div>
            <input class="text-input-md" placeholder="Login" bind:value={login}/>
        </div>
        <div>
            <input class="text-input-md" type="password" placeholder="Password" bind:value={password}/>
        </div>

        <button class="btn-md" on:click={onLogin}>Login</button>

    </div>

</div>