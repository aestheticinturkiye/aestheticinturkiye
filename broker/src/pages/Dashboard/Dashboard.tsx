"use client";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselNext,
  CarouselPrevious,
} from "@/components/ui/carousel";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import {
  DropdownMenu,
  DropdownMenuCheckboxItem,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import {
  Pagination,
  PaginationContent,
  PaginationItem,
} from "@/components/ui/pagination";
import { Progress } from "@/components/ui/progress";
import { Separator } from "@/components/ui/separator";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { getPartnerRequests } from "@/services/Partner";
import useAuth from "@/store/store";
import { useQuery } from "@tanstack/react-query";
import {
  ChevronLeft,
  ChevronRight,
  Copy,
  CreditCard,
  Download,
  File,
  ListFilter,
  MoreVertical,
  Send,
  Truck,
} from "lucide-react";
import { useEffect, useState } from "react";
import JSZip from "jszip";
import { saveAs } from "file-saver";
import { SendProposalModal } from "./SendProposalModal";
import { Proposals } from "./Proposals";

export const Dashboard = () => {
  // const user = useAuth((state) => state.accessToken);
  // const [data, setData] = useState();
  const { isPending, isError, data, error } = useQuery({
    queryKey: ["find-broker-request"],
    queryFn: getPartnerRequests,
  });
  const user = useAuth((state) => state.user);
  console.log("user", user);
  const [selectedRow, setSelectedRow] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isSendProposalModalOpen, setIsSendProposalModalOpen] = useState(false);
  const [currentImageIndex, setCurrentImageIndex] = useState(0);

  const handleImageClick = (index) => {
    setCurrentImageIndex(index);
    setIsModalOpen(true);
  };

  const handleDownload = () => {
    const zip = new JSZip();
    console.log("selectedRow.imageUrls", selectedRow.imageUrls);
    const downloadPromises = selectedRow?.imageUrls.map((url, index) => {
      const filename = `image-${index + 1}.jpg`;
      const xhr = new XMLHttpRequest();
      xhr.open("GET", url, true);
      xhr.responseType = "blob";

      return new Promise((resolve, reject) => {
        xhr.onload = function () {
          if (xhr.status === 200) {
            const blob = new Blob([xhr.response], { type: "image/jpeg" });
            zip.file(filename, blob);
            resolve();
          } else {
            reject(`Failed to download ${filename}`);
          }
        };

        xhr.onerror = function () {
          reject(`Failed to download ${filename}`);
        };

        xhr.send();
      });
    });

    // After all promises are resolved, generate zip file and trigger download
    Promise.all(downloadPromises)
      .then(() => {
        zip.generateAsync({ type: "blob" }).then((content) => {
          saveAs(content, "images.zip");
        });
      })
      .catch((error) => {
        console.error("Failed to download images:", error);
      });
  };
  return (
    <div className="grid flex-1 items-start gap-4 p-4 sm:px-6 sm:py-0 md:gap-8 lg:grid-cols-3 xl:grid-cols-3">
      <div className="grid auto-rows-max items-start gap-4 md:gap-8 lg:col-span-2">
        <div className="grid gap-4 sm:grid-cols-2 md:grid-cols-4 lg:grid-cols-2 xl:grid-cols-4">
          <Card className="sm:col-span-2" x-chunk="dashboard-05-chunk-0">
            <CardHeader className="pb-3">
              <CardTitle>{user.name}</CardTitle>
              <CardDescription className="max-w-lg text-balance leading-relaxed">
                Introducing Our Dynamic Orders Dashboard for Seamless Management
                and Insightful Analysis.
              </CardDescription>
            </CardHeader>
            <CardFooter>
              <Button>Create New Order</Button>
            </CardFooter>
          </Card>
          <Card x-chunk="dashboard-05-chunk-1">
            <CardHeader className="pb-2">
              <CardDescription>This Week</CardDescription>
              <CardTitle className="text-4xl">$1,329</CardTitle>
            </CardHeader>
            <CardContent>
              <div className="text-xs text-muted-foreground">
                +25% from last week
              </div>
            </CardContent>
            <CardFooter>
              <Progress value={25} aria-label="25% increase" />
            </CardFooter>
          </Card>
          <Card x-chunk="dashboard-05-chunk-2">
            <CardHeader className="pb-2">
              <CardDescription>This Month</CardDescription>
              <CardTitle className="text-4xl">$5,329</CardTitle>
            </CardHeader>
            <CardContent>
              <div className="text-xs text-muted-foreground">
                +10% from last month
              </div>
            </CardContent>
            <CardFooter>
              <Progress value={12} aria-label="12% increase" />
            </CardFooter>
          </Card>
        </div>
        <Tabs defaultValue="requests">
          <div className="flex items-center">
            <TabsList>
              <TabsTrigger value="requests">Requests</TabsTrigger>
              <TabsTrigger value="proposals">Proposals</TabsTrigger>
              <TabsTrigger value="Operations">Operations</TabsTrigger>
            </TabsList>
            <div className="ml-auto flex items-center gap-2">
              <DropdownMenu>
                <DropdownMenuTrigger asChild>
                  <Button
                    variant="outline"
                    size="sm"
                    className="h-7 gap-1 text-sm"
                  >
                    <ListFilter className="h-3.5 w-3.5" />
                    <span className="sr-only sm:not-sr-only">Filter</span>
                  </Button>
                </DropdownMenuTrigger>
                <DropdownMenuContent align="end">
                  <DropdownMenuLabel>Filter by</DropdownMenuLabel>
                  <DropdownMenuSeparator />
                  <DropdownMenuCheckboxItem checked>
                    Fulfilled
                  </DropdownMenuCheckboxItem>
                  <DropdownMenuCheckboxItem>Declined</DropdownMenuCheckboxItem>
                  <DropdownMenuCheckboxItem>Refunded</DropdownMenuCheckboxItem>
                </DropdownMenuContent>
              </DropdownMenu>
              <Button size="sm" variant="outline" className="h-7 gap-1 text-sm">
                <File className="h-3.5 w-3.5" />
                <span className="sr-only sm:not-sr-only">Export</span>
              </Button>
            </div>
          </div>
          <TabsContent value="requests">
            <Card x-chunk="dashboard-05-chunk-3">
              <CardHeader className="px-7">
                <CardTitle>Requests</CardTitle>
                <CardDescription>
                  Recent orders from your store.
                </CardDescription>
              </CardHeader>
              <CardContent>
                <Table>
                  <TableHeader>
                    <TableRow>
                      <TableHead>Name</TableHead>
                      <TableHead className="hidden sm:table-cell">
                        Surname
                      </TableHead>
                      <TableHead className="hidden sm:table-cell">
                        Email
                      </TableHead>
                      <TableHead className="hidden md:table-cell">
                        Phone
                      </TableHead>
                      <TableHead className="hidden md:table-cell">
                        Aesthetic Type
                      </TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {data?.data.list?.map((request, index) => (
                      <TableRow
                        key={index}
                        onClick={() => setSelectedRow(request)}
                        className="cursor-pointer"
                      >
                        <TableCell>{request.client.name}</TableCell>
                        <TableCell className="hidden sm:table-cell">
                          {request.client.surname || "N/A"}
                        </TableCell>
                        <TableCell className="hidden md:table-cell">
                          {request.client.email}
                        </TableCell>
                        <TableCell className="hidden md:table-cell">
                          {request.client.phone}
                        </TableCell>
                        <TableCell className="hidden md:table-cell">
                          {request.aestheticType}
                        </TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </CardContent>
            </Card>
          </TabsContent>

          <TabsContent value="proposals">
            <Proposals />
          </TabsContent>
        </Tabs>
      </div>
      <div>
        <Card className="overflow-hidden" x-chunk="dashboard-05-chunk-4">
          {selectedRow ? (
            <>
              <CardHeader className="flex flex-row items-start bg-muted/50">
                <div className="grid gap-0.5">
                  <CardTitle className="group flex items-center gap-2 text-lg">
                    {selectedRow.client.name + " " + selectedRow.client.surname}
                    <Button
                      size="icon"
                      variant="outline"
                      className="h-6 w-6 opacity-0 transition-opacity group-hover:opacity-100"
                    >
                      <Copy className="h-3 w-3" />
                      <span className="sr-only">Copy Order ID</span>
                    </Button>
                  </CardTitle>
                  <CardDescription>
                    {selectedRow.aestheticType}
                    {/* {new Date(selectedRow.preferedDate).toLocaleDateString()} */}
                  </CardDescription>
                </div>
                <div className="ml-auto flex items-center gap-1">
                  <Button
                    onClick={() => setIsSendProposalModalOpen(true)}
                    size="sm"
                    variant="outline"
                    className="h-8 gap-1"
                  >
                    <Send className="h-3.5 w-3.5" />
                    <span className="sr-only sm:not-sr-only">
                      Send Proposal
                    </span>
                  </Button>
                  {/* <Button size="sm" variant="outline" className="h-8 gap-1">
                    <CreditCard className="h-3.5 w-3.5" />
                    <span className="sr-only sm:not-sr-only">Pay</span>
                  </Button> */}
                  <DropdownMenu>
                    <DropdownMenuTrigger asChild>
                      <Button
                        size="sm"
                        variant="outline"
                        className="h-8 w-8 p-0"
                      >
                        <MoreVertical className="h-4 w-4" />
                        <span className="sr-only">Open Menu</span>
                      </Button>
                    </DropdownMenuTrigger>
                    <DropdownMenuContent align="end">
                      <DropdownMenuItem>View Details</DropdownMenuItem>
                      <DropdownMenuSeparator />
                      <DropdownMenuItem>Delete Order</DropdownMenuItem>
                    </DropdownMenuContent>
                  </DropdownMenu>
                </div>
              </CardHeader>
              <CardContent className="grid gap-8 px-1 py-6 md:grid-cols-2 lg:px-4">
                <div className="grid gap-3">
                  <div className="flex items-center gap-4">
                    <div className="grid gap-1">
                      <span className="text-sm font-medium leading-none">
                        Status
                      </span>
                      <Badge className="w-max">Paid</Badge>
                    </div>
                  </div>
                  <div className="space-y-1">
                    <p className="text-sm font-medium leading-none">Title</p>
                    <p className="text-sm text-muted-foreground">
                      {selectedRow.aestheticType}
                    </p>
                  </div>
                  <div className="space-y-1">
                    <p className="text-sm font-medium leading-none">
                      Description
                    </p>
                    <p className="text-sm text-muted-foreground">
                      {selectedRow.description}
                    </p>
                  </div>
                  <div className="space-y-1">
                    <p className="text-sm font-medium leading-none">City</p>
                    <p className="text-sm text-muted-foreground">
                      {selectedRow.preferredCity || "N/A"}
                    </p>
                  </div>
                  <div className="space-y-1">
                    <p className="text-sm font-medium leading-none">Date</p>
                    <p className="text-sm text-muted-foreground">
                      {new Date(selectedRow.preferedDate).toLocaleDateString()}
                    </p>
                  </div>
                  <div className="space-y-1">
                    <p className="text-sm font-medium leading-none">
                      Accommodation
                    </p>
                    <p className="text-sm text-muted-foreground">
                      {selectedRow.neededAccommodation ? "Yes" : "No"}
                    </p>
                  </div>
                  <div className="space-y-1">
                    <p className="text-sm font-medium leading-none">
                      Transportation
                    </p>
                    <p className="text-sm text-muted-foreground">
                      {selectedRow.neededTransportation ? "Yes" : "No"}
                    </p>
                  </div>
                </div>
                <div className="grid gap-2">
                  <div className="space-y-1">
                    <p className="text-sm font-medium leading-none">Images</p>
                    <div className="flex gap-2">
                      {selectedRow.imageUrls.length > 0 ? (
                        selectedRow.imageUrls.map((url, i) => (
                          <div key={i} className="relative">
                            <img
                              src={url}
                              alt={`Image ${i + 1}`}
                              className="h-12 w-12 rounded-md object-cover cursor-pointer"
                              onClick={() => handleImageClick(i)}
                            />
                          </div>
                        ))
                      ) : (
                        <p>No Images</p>
                      )}
                    </div>
                  </div>
                </div>
              </CardContent>
            </>
          ) : (
            <CardContent className="flex p-2 items-center justify-center">
              <p>Select a row to see details</p>
            </CardContent>
          )}
        </Card>
      </div>

      {selectedRow && (
        <Dialog open={isModalOpen} onOpenChange={setIsModalOpen}>
          {/* <DialogTrigger asChild>
            <Button>Open Image Carousel</Button>
          </DialogTrigger> */}
          <DialogContent className="w-full">
            <DialogHeader>
              <DialogTitle>Image Carousel</DialogTitle>
              <DialogDescription>
                Browse through the images and download them if needed.
              </DialogDescription>
            </DialogHeader>
            <Carousel>
              <CarouselContent>
                {selectedRow?.imageUrls.map((url, i) => (
                  <CarouselItem key={i}>
                    <div className="p-1 w-full">
                      <a href={url} target="blank">
                        <img
                          className="w-full h-96"
                          src={url}
                          alt={`Image ${i + 1}`}
                        />
                      </a>
                    </div>
                  </CarouselItem>
                ))}
              </CarouselContent>
              <CarouselPrevious />
              <CarouselNext />
            </Carousel>
            <DialogFooter>
              <Button variant="ghost" onClick={() => setIsModalOpen(false)}>
                Close
              </Button>
            </DialogFooter>
          </DialogContent>
        </Dialog>
      )}

      <SendProposalModal
        requestId={selectedRow?.id}
        isOpen={isSendProposalModalOpen}
      />
    </div>
  );
};
