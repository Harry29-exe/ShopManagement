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

<div class="w-full v-stack mt-10 widget-lg w-11/12 md:w-9/12 xl:w-7/12">

    <div class="text-4xl font-bold mb-12">Invoices menu</div>
    <div class="flex-grid">
        {#each subPages as page}
            <button class="card-md card-hover w-[200px] h-[100px]" on:click={() => onLinkClick(page)}>
                {page.name}
            </button>
        {/each}
    </div>
</div>


