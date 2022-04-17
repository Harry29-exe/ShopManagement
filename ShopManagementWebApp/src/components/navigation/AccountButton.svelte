<script lang="ts">
    import {authStore} from "../../stores/AuthStore";

    let open: boolean = false
</script>

<div class="relative">
    <div class="account-button center" on:click={() => open = !open}>

        {$authStore.username.substring(0,1)}

    </div>

    {#if open}
        <div class="menu">
            <div class="menu-btn">first</div>
            <div class="menu-btn">first</div>
            <div class="menu-btn" on:click={() => authStore.logout()}>Logout</div>

        </div>
    {/if}
</div>

<style>

    .account-button {
        @apply rounded-[100%] w-8 h-8 bg-bg-400 relative
        hover:shadow-dark-sm hover:cursor-pointer;
    }

    .menu {
        @apply absolute w-52 bg-bg-200 mt-1 right-0
            shadow-dark-md py-3 rounded-md text-base
        ;
    }

    .menu-btn {
        @apply w-full text-lg px-2 hover:bg-black-alpha-200 hover:cursor-pointer;
    }

</style>