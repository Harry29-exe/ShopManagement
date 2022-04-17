<script lang="ts">
    import {authStore} from "../stores/AuthStore";
    import {AppMessage, popupStore} from "../stores/PopupStore";
    import {LoginRequest} from "../apiclient/LoginClient";

    let login: string = ""
    let password: string = ""
    let dontLogout: boolean = false

    const onLogin = () => {
        authStore.login(new LoginRequest(login, password, dontLogout))
            .then(result => {
                if (result instanceof AppMessage) {
                    popupStore.setNew(result);
                }
            })
    }
</script>

<div class="w-full h-full mt-32 center">

    <div class="v-stack md:min-w-[400px] py-5 px-2 bg-bg-200 rounded-md shadow-dark-md mx-3">
        <div class="text-3xl w-full center py-4">Login</div>
        <div class="min-w-[240px] max-w-[70%]">
            <input class="text-input-md w-full" placeholder="Login" bind:value={login}/>

            <input class="text-input-md w-full" type="password" placeholder="Password" bind:value={password}/>

            <label class="w-full center text-lg">
                <input type="checkbox" class="mx-2 scale-[1.25]" bind:checked={dontLogout}/>
                Don't logout me
            </label>
        </div>

        <button class="btn-md" on:click={onLogin}>Login</button>

    </div>

</div>