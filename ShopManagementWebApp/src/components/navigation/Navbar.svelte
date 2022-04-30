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
        {name: "Invoices", link: "/invoices"},
        {name: "Items", link: "/items"},
        {name: "Entities", link: "/entities"},
        {name: "Reports", link: "/reports"}
    ]

    const loginPages: PageLink[] = [
        {name: "Login", link: "/login"}
    ]

    const onLinkClick = (link: PageLink) => {
        goto(link.link)
    }
</script>


<div class="w-full h-14 bg-bg-200 h-stack py-1 px-4 box-border shadow-dark-sm">
    {#each pages as page}
        <div class="link" on:click={() => onLinkClick(page)}>
            {page.name}
        </div>
    {/each}

    <div class="flex-grow"></div>

    {#if $authStore.loggedIn}
        <AccountButton/>
    {:else}
        {#each loginPages as page}
        <div class="link" on:click={() => onLinkClick(page)}>
            {page.name}
        </div>
        {/each}
    {/if}

</div>

