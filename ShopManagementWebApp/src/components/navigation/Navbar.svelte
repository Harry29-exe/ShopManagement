<script lang="ts">
    import {goto} from "$app/navigation";
    import AccountButton from "./AccountButton.svelte";
    import {authStore} from "../../stores/AuthStore";

    interface PageLink {
        name: string;
        link: string
    }

    const pages: PageLink[] = [
        {name: "Home", link: "/"},
        {name: "Sellers", link: "/sellers"}
    ]

    const loginPages: PageLink[] = [
        {name: "Login", link: "/login"}
    ]

    const onLinkClick = (link: PageLink) => {
        goto(link.link)
    }
</script>


<div class="w-full h-14 bg-bg-200 h-stack py-1 px-4 box-border">
    {#each pages as page}
        <div class="link" on:click={() => onLinkClick(page)}>
            {page.name}
        </div>
    {/each}

    <div class="flex-grow"></div>

    {#if authStore.isLogged()}
        <AccountButton/>
    {:else}
        {#each loginPages as page}
        <div class="link" on:click={() => onLinkClick(page)}>
            {page.name}
        </div>
        {/each}
    {/if}

</div>

