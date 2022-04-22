<script lang="ts">
    import {SalesInvoiceDTO} from "../../dto/SalesInvoiceDTO";
    import {CompanyInfoDTO} from "../../dto/CompanyInfo";
    import {onMount} from "svelte";
    import {CompanyInfoClient} from "../../apiclient/CompanyInfoClient";
    import SalesInvoiceInfo from "./SalesInvoiceInfo.svelte";
    import ProductTable from "../table/ProductTable.svelte";

    export let invoice: SalesInvoiceDTO;
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
    <div class="header-md">Sales Invoice</div>
    <SalesInvoiceInfo invoice={invoice}/>


    <div class="text-2xl font-bold my-2">Invoice products</div>
    <ProductTable products={invoice.products}/>
    {/if}
</div>

