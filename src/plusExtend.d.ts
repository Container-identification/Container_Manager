declare const plus: plus;

interface Window {
  plus: any;
}

interface plus {
  camera: camera;
  gallery: gallery;
  uploader: uploader;
  storage: PlusStorage;
}

// camera
interface camera {
  getCamera: (index?: number) => cameraObject;
}

interface cameraObject {
  readonly supportedImageResolutions: [string];
  readonly supportedVideoResolutions: [string];
  readonly supportedImageFormats: [string];
  readonly supportedVideoFormats: [string];
  captureImage: (
    successCB: (captureFile: string) => void,
    errorCB: (error: any) => void,
    options: cameraOptions
  ) => void;
  startVideoCapture: (
    successCB: (captureFile: string) => void,
    errorCB: (error: any) => void,
    options: cameraOptions
  ) => void;
  stopVideoCapture: () => void;
}

interface cameraOptions {
  filename?: string;
  format?: string;
  index?: string;
  videoMaximumDuration?: number;
  optimize?: boolean;
  resolution?: string;
  popover?: popPosition;
}

interface popPosition {
  top: string;
  left: string;
  width: string;
  height: string;
}

//uploader
interface uploader {
  createUpload: (
    url: string,
    options: uploadOptions,
    completedCB: (upload: uploadObject, status: number) => void
  ) => uploadObject;
}

interface uploadObject {
  readonly id: any;
  readonly url: string;
  readonly state: number;
  readonly options: uploadOptions;
  readonly uploadedSize: number;
  readonly totalSize: number;
  readonly responseText: any;
  abort: () => void;
  addFile: (path: string, options: uploadFileOptions) => boolean;
  addData: (key: string, value: string) => boolean;
  start: () => void;
  pause: () => void;
  resume: () => void;
  addEventListener: (
    type: uploadEvent,
    listener: (upload: uploadObject, status: number) => void
  ) => void;
}

interface uploadOptions {
  chunkSize?: number;
  method?: string;
  priority?: number;
  retry?: number;
  retryInterval?: number;
  timeout?: number;
}

interface uploadFileOptions {
  key?: string;
  name?: string;
  mime?: string;
}

type uploadEvent = "statechanged";

//gallery
interface gallery {
  pick: (
    succesCB: (file: string) => void,
    errorCB: (error: any) => void,
    options: galleryOptions
  ) => void;
}

interface galleryOptions {
  animation?: boolean;
  filename?: string;
  filter?: "image" | "video" | "none";
  maximum?: number;
  multiple?: boolean;
  permissionAlert?: boolean;
  popover?: popPosition;
  selected?: [string];
  onmaxed?: Function;
}

//storage
interface PlusStorage {
  getLength: () => number;
  getItem: (key: string) => string;
  getItemAsync: (
    key: string,
    successCB: (event: any) => void,
    errorCB: (err: any) => void
  ) => void;
  key: (index: number) => string;
  setItem: (key: string, value: string) => void;
  setItemAsync: (
    key: string,
    value: string,
    success: (event: any) => void,
    errorCB: (err: any) => void
  ) => void;
  removeItem: (key: string) => void;
  removeItemAsync: (
    key: string,
    successCB: (event: any) => void,
    errorCB: (err: any) => void
  ) => void;
  getAllKeys: () => string[];
}
