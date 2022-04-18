<script lang="ts">
    import {goto} from "$app/navigation"
    interface SubPage {
        name: string,
        link: string
    }
    const pagePrefix = "/invoices";
    const subPages: SubPage[] = [
        {name: "Purchase Invoices", link: "/purchase"},
        {name: "Sales Invoices", link: "/sales"}
    ]

    const onLinkClick = (page: SubPage) => {
        goto(pagePrefix + page.link);
    }
</script>

<div class="w-full center mt-10">
    <div class="flex-grid">
        {#each subPages as page}
            <button class="btn-lg" on:click={() => onLinkClick(page)}>{page.name}</button>
        {/each}
    </div>
</div>


