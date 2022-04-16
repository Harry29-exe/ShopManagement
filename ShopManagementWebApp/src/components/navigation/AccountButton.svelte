<script lang="ts">
    import {authStore} from "../../stores/AuthStore";

    let open: boolean = false
</script>

<div class="relative">
    <div class="account-button hover:shadow-lg hover:cursor-pointer">

        {authStore.username.substring(0,1)}

    </div>

    <div class="menu">
        <div class="menu-btn hover:bg-black-alpha-200 hover:cursor-pointer">first</div>
        <div class="menu-btn">first</div>
        <div class="menu-btn">first</div>

    </div>
</div>

<style>

    .account-button {
        @apply rounded-[100%] w-8 h-8 bg-bg-400 center relative;
    }

    .menu {
        @apply absolute w-52 bg-bg-200 mt-1 right-0
            shadow-dark-md py-3 rounded-md text-base
        ;
    }

    .menu-btn {
        @apply w-full text-lg px-2;
    }

</style>