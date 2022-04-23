<script lang="ts">
    import {CompanyInfoDTO} from "../../dto/CompanyInfo";
    import {onMount} from "svelte";
    import {CompanyInfoClient} from "../../apiclient/CompanyInfoClient";
    import ProductTable from "../table/ProductTable.svelte";
    import {PurchaseInvoiceDTO} from "../../dto/PurchaseInvoiceDTOs";
    import PurchaseInvoiceInfo from "./PurchaseInvoiceInfo.svelte";

    export let invoice: PurchaseInvoiceDTO;
    let companyInfo: CompanyInfoDTO;


    onMount(async () => {
        let result = await CompanyInfoClient.getInfo();
        if (result.ok && result.result) {
            companyInfo = result.result;
        }
    })
</script>

<div class="bg-bg-200 p-4 min-w-[320px] min-h-[160px] mx-auto rounded-md shadow-dark-md v-stack">
    {#if invoice}
        <div class="header-md">Purchase Invoice</div>
        <PurchaseInvoiceInfo invoice={invoice}/>


        <div class="text-2xl font-bold my-2">Invoice products</div>
        <ProductTable products={invoice.products}/>
    {/if}
</div>