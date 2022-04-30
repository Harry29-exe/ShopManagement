<script lang="ts">
    import Table from "../../components/table/Table.svelte";
    import {TableHeader} from "../../components/table/TableHeader";
    import {SellerSalesReportDTO} from "../../dto/SalesReportsDTOs";
    import {onMount} from "svelte";
    import {SalesReportsClient} from "../../apiclient/SalesReportsClient";
    import {AppMessage, popupStore} from "../../stores/PopupStore";

    const tHeader: TableHeader<SellerSalesReportDTO> = TableHeader.fromArray([
        ["Seller user id", report => report.sellerUserId],
        ["Seller fullname", report => report.sellerFullname],
        ["Transactions made", report => report.transactions],
        ["Sales total value", report => report.salesTotal],
    ])

    let from: String;
    let to: String;
    let reports: SellerSalesReportDTO[] = [];

    async function fetchReport() {
        console.log((new Date(from)).getTime())
        let result = await SalesReportsClient.getSellersResults(
            new Date(from),
            new Date(to))

        if (result.ok && result.result) {
            reports = result.result.statistics
            console.log(reports)
        } else {
            popupStore.setNew(result.msg)
        }
    }
</script>


<div class="widget-lg w-container-md v-stack px-[5%]">

    <p class="header-lg">Sellers sales results</p>

    <div class="h-stack mb-8">
        <div class="input-wrapper-sm">
            <p>From Date</p>
            <input bind:value={from} type="date"/>
        </div>
        <div class="input-wrapper-sm">
            <p>To Date</p>
            <input bind:value={to} type="date"/>
        </div>
    </div>

    <button on:click={fetchReport}
        class="btn-md">
        Show Results
    </button>

    <Table header={tHeader} values={reports}/>

</div>